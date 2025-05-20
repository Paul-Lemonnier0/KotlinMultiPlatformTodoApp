package org.example.project.di

import com.russhwolf.settings.Settings
import org.example.project.domain.Localization
import org.example.project.models.TodoList
import org.example.project.viewModels.LanguageViewModel
import org.example.project.viewModels.ThemeViewModel
import org.example.project.viewmodels.TodoViewModel
import org.koin.dsl.module

/**
 * All the modules that will be used in the app. (Language, theme, todoList, todoViewModel)
 */
val appModule = module {
    single { ThemeViewModel() }

    single { LanguageViewModel(localization = get()) }

    single { TodoList() }

    single {
        TodoViewModel(
            client = get(),
            todoList = get()
        )
    }
}
