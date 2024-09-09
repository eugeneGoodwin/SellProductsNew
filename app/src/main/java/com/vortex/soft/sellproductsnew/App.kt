package com.vortex.soft.sellproductsnew

import android.app.Application
import com.vortex.soft.sellproductsnew.di.component.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appComponent)
        }
        Timber.plant(Timber.DebugTree())
    }
}