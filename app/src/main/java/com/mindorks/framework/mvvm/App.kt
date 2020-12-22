package com.mindorks.framework.mvvm

import android.app.Application
import com.mindorks.framework.mvvm.di.apiServiceModule
import com.mindorks.framework.mvvm.di.viewModelFactoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(apiServiceModule, viewModelFactoryModule))
        }
    }
}
