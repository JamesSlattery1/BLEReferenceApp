package com.example.blereferenceapp

import android.content.Context
import com.example.blereferenceapp.AllModules
import com.example.blereferenceapp.BLEApp
import com.example.blereferenceapp.ui.AppContext
import com.example.blereferenceapp.ui.ServiceDIModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Main component for the application.
 *
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AllModules::class,
        ServiceDIModule::class,
        AndroidSupportInjectionModule::class,
        InjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<BLEApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}