package com.example.blereferenceapp

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import com.example.blereferenceapp.DevicesModule
import javax.inject.Inject

class DevicesModuleImpl @Inject constructor(
    private val context: Context
) : DevicesModule {
    override fun getIMEI(): String? {
        val deviceId: String

        deviceId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        } else {
            val mTelephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (mTelephony.deviceId != null) {
                mTelephony.deviceId
            } else {
                Settings.Secure.getString(
                    context.contentResolver,
                    Settings.Secure.ANDROID_ID
                )
            }
        }
        Log.d("DevicesModuleImpl", "deviceImei:$deviceId ")
        return deviceId
    }

}