package com.example.blereferenceapp.ui

import com.example.blereferenceapp.BeaconService

import android.app.Service
import com.example.blereferenceapp.BeaconServiceImpl

import com.example.blereferenceapp.DevicesModule
import com.example.blereferenceapp.BeaconsModule


import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceDIModule {


    @Provides
    @Singleton
    fun beaconServiceProvide(beaconServiceImpl: BeaconServiceImpl) = beaconServiceImpl as BeaconService


}