package com.example.blereferenceapp

import androidx.lifecycle.ViewModel
import com.example.blereferenceapp.ui.ViewModelKey
import com.example.blereferenceapp.AlertViewModel
import com.example.blereferenceapp.MainViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AlertViewModel::class)
    abstract fun bindViewModel(viewModel: AlertViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}