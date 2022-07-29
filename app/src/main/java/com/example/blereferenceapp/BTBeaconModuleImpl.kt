package com.example.blereferenceapp



import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothAdapter.LeScanCallback
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.collection.arraySetOf
import androidx.lifecycle.MutableLiveData
import com.example.blereferenceapp.BeaconModel
import com.example.blereferenceapp.BeaconsModule
import kotlinx.coroutines.delay
import okhttp3.internal.and
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class BTBeaconModuleImpl @Inject constructor(context: Context) : BeaconsModule {
    private val TAG = BTBeaconModuleImpl::class.java.simpleName
    private var mBluetoothAdapter: BluetoothAdapter = (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    private val beacons = MutableLiveData<List<BeaconModel>>()
    private val tempBeacon = ArrayList<BeaconModel>()
    private val handler = Handler(Looper.getMainLooper())

    override fun getBeaconsUpdate() = beacons


    init {
        startUpdate()
    }

    private fun startUpdate() {
        handler.postDelayed(
            {
                beacons.postValue(ArrayList(tempBeacon))
                tempBeacon.clear()
                startUpdate()
            }, 3000
        )
    }

    override fun start() {
//        if (mBluetoothAdapter.isDiscovering) {
        mBluetoothAdapter.startLeScan(mLeScanCallback)
//        }


    }


    override fun stop() {
        mBluetoothAdapter.stopLeScan(mLeScanCallback)
    }


    private val mLeScanCallback = LeScanCallback { device: BluetoothDevice, rssi: Int, scanRecord: ByteArray ->
        var startByte = 2
        var patternFound = false
        while (startByte <= 5) {
            if (scanRecord[startByte + 2].toInt() and 0xff == 0x02 &&  // Identifies an Beacon
                scanRecord[startByte + 3].toInt() and 0xff == 0x15
            ) { // Identifies correct data length
                patternFound = true
                break
            }
            startByte++
        }
        if (patternFound) {
            val uuidBytes = ByteArray(16)
            System.arraycopy(scanRecord, startByte + 4, uuidBytes, 0, 16)
            val hexString: String = bytesToHex(uuidBytes)
            val uuid = (hexString.substring(0, 8) + "-"
                    + hexString.substring(8, 12) + "-"
                    + hexString.substring(12, 16) + "-"
                    + hexString.substring(16, 20) + "-"
                    + hexString.substring(20, 32))
            val major: Int = (scanRecord[startByte + 20] and 0xff.toByte().toInt()) * 0x100 + (scanRecord[startByte + 21] and 0xff.toByte()
                .toInt())
            val minor: Int = (scanRecord[startByte + 22] and 0xff.toByte().toInt()) * 0x100 + (scanRecord[startByte + 23] and 0xff.toByte()
                .toInt())
            val ibeaconName = device.name
            val mac = device.address
            val txPower = scanRecord[startByte + 24].toInt()
            val beacon = BeaconModel(uuid, mac, major, minor, txPower, rssi)
            Log.d(TAG, ": " + ibeaconName + " " + uuid + " " + mac + " " + major + " " + minor + " " + txPower + " " + rssi)
            tempBeacon.add(beacon)
        }
    }


    private fun bytesToHex(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = bytes[j] and 0xFF.toByte().toInt()
            hexChars[j * 2] = hexArray.get(v ushr 4)
            hexChars[j * 2 + 1] = hexArray.get((v and 0x0F).toInt())
        }
        return String(hexChars)
    }

    val hexArray = "0123456789ABCDEF".toCharArray()

}


