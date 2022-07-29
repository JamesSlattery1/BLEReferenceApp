package com.example.blereferenceapp

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.example.blereferenceapp.NavGraphXmlDirections
import com.example.blereferenceapp.EventDataModel
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject



class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    private var localMassAlertState = false

    private var backPushCounter : Int = 0
    private var isTimerRunning = false

    val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.CALL_PHONE
        //Manifest.permission.ACCESS_NOTIFICATION_POLICY
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*   val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
           if (!notificationManager.isNotificationPolicyAccessGranted) {
               val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
               startActivity(intent)
           }*/
        //for turning on screen when alert raised from screen locked
//        window.addFlags(
//            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
//                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
//                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
//                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
//        )

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
//            setShowWhenLocked(true)
//            setTurnScreenOn(true)
//            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
//            keyguardManager.requestDismissKeyguard(this, null)
//        } else {
//            this.window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
//                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
//                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
//            //android.R.attr.turnScreenOn
//        }

        setContentView(R.layout.screen_alert)
//        clearPreviousState()
        requestPermission()
        hideStatusBar()
    }

    private fun moveToMainScreen() {
        findNavController(R.id.nav_host_fragment).navigate(NavGraphXmlDirections.actionGlobalAlertFragment(), NavOptions.Builder().setLaunchSingleTop(true).build())
    }

    private fun moveToCancelAlert() {
        findNavController(R.id.nav_host_fragment).navigate(NavGraphXmlDirections.actionGlobalStopAlertFragment())
    }

    private fun hideStatusBar() {
        val decorView = window.decorView
        val uiOptions: Int = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions
        val actionBar = actionBar
        actionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent()
            val packageName = packageName
            val pm = getSystemService(POWER_SERVICE) as PowerManager
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.action = ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                intent.data = Uri.parse("package:$packageName")
                startActivity(intent)
            }
        }

        ActivityCompat.requestPermissions(this, permissions, 1)
    }



    private fun startTimerKill(timeWindow : Int) {
        isTimerRunning = true
        val timerTask: TimerTask
        val timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                isTimerRunning = false
                backPushCounter = 0
            }
        }
        timer.schedule(timerTask, timeWindow.toLong())
    }
}
