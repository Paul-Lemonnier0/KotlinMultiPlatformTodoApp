package org.example.project

import android.app.Application
import org.example.project.di.initKoin
import org.example.project.di.provideKoinAppContext
import org.koin.android.ext.koin.androidContext

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        provideKoinAppContext(this)
        initKoin(
            config = {androidContext(this@MyApp)}
        )
        println("Koin started in MyApp")
    }
}
