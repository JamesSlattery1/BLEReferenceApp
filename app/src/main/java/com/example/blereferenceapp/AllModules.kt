package com.example.blereferenceapp



import com.example.blereferenceapp.BTBeaconModuleImpl
import com.example.blereferenceapp.DevicesModuleImpl

import com.example.blereferenceapp.BeaconsModule.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


class AllModules {
    @Provides
    @Singleton
    fun beaconsModuleProvide(beaconsModule: BTBeaconModuleImpl) = beaconsModule as BeaconsModule
}