package com.example.blereferenceapp

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.example.blereferenceapp.ConnectionLiveData

import com.example.blereferenceapp.BaseFragment
import kotlinx.android.synthetic.main.screen_alert.*


class AlertFragment : BaseFragment(R.layout.screen_alert) {
    private val viewModel by viewModels<AlertViewModel> { viewModelFactory }
    lateinit var connectionLiveData: ConnectionLiveData

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectionLiveData = context?.let { ConnectionLiveData(it) }!!

        viewModel.deviceId.observe(viewLifecycleOwner, Observer { deviceID ->
            deviceId.text = deviceID
        })

        viewModel.init()

        viewModel.batteryPercentage.observe(viewLifecycleOwner, Observer { batteryPercentage ->
            battery.text = "$batteryPercentage%"
        })


    }


}