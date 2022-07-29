package com.example.blereferenceapp


import com.example.blereferenceapp.ViewModelModule
import com.example.blereferenceapp.AlertFragment
import com.example.blereferenceapp.MainActivity
import com.example.blereferenceapp.ui.ViewModelBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectionModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class, ViewModelModule::class])
    internal abstract fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class, ViewModelModule::class])
    internal abstract fun authFragmentInjector(): AlertFragment

}