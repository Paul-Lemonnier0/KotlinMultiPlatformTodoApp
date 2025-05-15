// shared/src/iosMain/kotlin/org/example/project/di/HttpClientModule.kt
package org.example.project.di

import io.ktor.client.engine.darwin.Darwin
import org.example.project.models.TodoListClient
import org.example.project.utils.createHttpClient
import org.koin.dsl.module

actual fun platformHttpClientModule() = module {
    single { TodoListClient(createHttpClient(Darwin.create())) }
}
