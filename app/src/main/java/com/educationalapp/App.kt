package com.educationalapp

import android.app.Application
import com.educationalapp.core.di.dispatchersModule
import com.educationalapp.core.di.koinModule
import com.educationalapp.features.home.VideoService
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    val videoService = VideoService()

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            modules(koinModule, dispatchersModule)
        }
    }
}