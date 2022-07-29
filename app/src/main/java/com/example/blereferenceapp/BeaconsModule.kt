package com.example.blereferenceapp

import androidx.lifecycle.LiveData
import com.example.blereferenceapp.BeaconModel

interface BeaconsModule{
    fun getBeaconsUpdate():LiveData<List<BeaconModel>>

    fun start();
    fun stop();
}