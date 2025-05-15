// shared/src/commonMain/kotlin/org/example/project/di/KoinInit.kt
package org.example.project.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

expect val targetModule: Module

fun initKoin(
    config: (KoinApplication.() -> Unit)? = null,
    extraModules: List<Module> = emptyList()
) {
    startKoin {
        config?.invoke(this)
        modules(targetModule + appModule + platformHttpClientModule() + extraModules)
    }
}