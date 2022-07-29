package com.example.blereferenceapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.blereferenceapp.RoomModel
import com.example.blereferenceapp.BaseViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


class AlertViewModel @Inject constructor(
    private val context: Context,
    private val beaconService: BeaconService,

) : BaseViewModel(context) {
    private var rooms: List<RoomModel> = ArrayList();
    // val currentRoom = getCurrentRooms()
    val deviceId = MutableLiveData<String>()
    val batteryPercentage =  MutableLiveData<Int>()
    val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager


    private val BatterybroadcastReceiver : BroadcastReceiver = object  : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            batteryPercentage.postValue(level)
        }
    }

    fun init() {

    }
    fun listenForInternetConnectivity() {
        val networkCallback: ConnectivityManager.NetworkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.d("networkType","network type is:" +  ConnectivityManager.TYPE_MOBILE)
            }
            override fun onLost(network: Network) {
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request = NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            connectivityManager.registerNetworkCallback(request, networkCallback)
        }
    }

    fun getNetworkType() : String {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val networkInfo: NetworkInfo? = connMgr?.activeNetworkInfo
        var networkType = ""
        var wifiConnected = false
        var cellularConnected = false

        if(networkInfo !=null && networkInfo.isConnectedOrConnecting) {
            wifiConnected = networkInfo.type == ConnectivityManager.TYPE_WIFI
            cellularConnected = networkInfo.type == ConnectivityManager.TYPE_MOBILE

            networkType = when {
                wifiConnected -> {
                    "WiFi"
                }
                cellularConnected -> {
                    "Cell"
                }
                else -> {
                    ""
                }
            }
        }
        return networkType

    }


}
