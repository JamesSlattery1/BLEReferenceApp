package com.example.blereferenceapp

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors
import javax.inject.Singleton


@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideDatabaseDispatcher(): CoroutineDispatcher {
        return Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    }

}