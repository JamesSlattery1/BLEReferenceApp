package com.example.blereferenceapp

import androidx.lifecycle.LiveData
import com.example.blereferenceapp.BeaconModel


interface BeaconService {


    fun getBeaconUpdates():LiveData<List<BeaconModel>>
    fun startUpdateBeacon();
    fun stopUpdateBeacon();

}