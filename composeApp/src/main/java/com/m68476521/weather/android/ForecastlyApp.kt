package com.m68476521.weather.android

import android.app.Application
import com.m68476521.weather.di.initKoin
import org.koin.android.ext.koin.androidContext

class ForecastlyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ForecastlyApp)
        }
    }
}