package org.example.project.di

import android.app.Application
import org.example.project.domain.Localization
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val targetModule = module {
    single<Localization> { Localization(context = androidContext()) }
}

// Optional: Hold a reference to the application context if needed elsewhere
lateinit var koinAppContext: Application

fun provideKoinAppContext(context: Application) {
    koinAppContext = context
}
