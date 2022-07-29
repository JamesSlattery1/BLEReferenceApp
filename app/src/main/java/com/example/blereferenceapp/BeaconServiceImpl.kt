package com.example.blereferenceapp

import com.example.blereferenceapp.BeaconsModule
import com.example.blereferenceapp.BeaconService
import javax.inject.Inject

class BeaconServiceImpl @Inject constructor(

    private val beaconsModule: BeaconsModule

) : BeaconService {


    override fun getBeaconUpdates() = beaconsModule.getBeaconsUpdate()

    override fun startUpdateBeacon() = beaconsModule.start()

    override fun stopUpdateBeacon() = beaconsModule.stop()
}