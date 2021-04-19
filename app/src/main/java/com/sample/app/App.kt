package com.sample.app

import android.app.Application
import com.sample.app.dependency_injection.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Vdovicenco Alexandr on 04/19/2021.
 */

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}