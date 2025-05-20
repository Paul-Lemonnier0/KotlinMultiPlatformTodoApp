package org.example.project

import CustomTheme
import androidx.compose.foundation.layout.padding

import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import dev.burnoo.compose.remembersetting.rememberStringSetting

import org.example.project.navigation.HomeTab
import org.example.project.navigation.SettingsTab
import org.example.project.viewModels.ThemeViewModel
import org.koin.compose.getKoin

import fontGrayColor
import org.example.project.domain.Language
import org.example.project.viewModels.LanguageViewModel

@Composable
fun App() {
    val themeViewModel = getKoin().get<ThemeViewModel>()
    val languageViewModel = getKoin().get<LanguageViewModel>()

    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()
    val shouldRecompose by languageViewModel.shouldRecreate.collectAsState()

    val languageIso = rememberStringSetting(
        key = "savedLanguageIso",
        defaultValue = Language.French.iso
    )

    val selectedLanguage = remember {
        derivedStateOf {
            Language.entries.first { it.iso == languageIso.value }
        }
    }

    LaunchedEffect(languageIso.value) {
        languageViewModel.setLanguage(selectedLanguage.value)
    }

    LaunchedEffect(shouldRecompose) {
        if (shouldRecompose) {
            languageViewModel.clearRecreateFlag()
        }
    }

    //Reload the app UI when the selected language changes
    key(selectedLanguage.value) {
        //Theme provider
        CustomTheme(isDarkTheme = isDarkTheme) {
            //Navigator (tabs, home by default)
            TabNavigator(HomeTab) { tabNavigator ->
                //Base layout
                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = MaterialTheme.colorScheme.secondary,
                        ) {
                            listOf(HomeTab, SettingsTab).forEach { tab ->
                                val options = tab.options
                                BottomNavigationItem(
                                    selected = tabNavigator.current == tab,
                                    onClick = { tabNavigator.current = tab },
                                    icon = {
                                        Icon(
                                            options.icon!!,
                                            contentDescription = options.title,
                                            tint = if (tabNavigator.current == tab) MaterialTheme.colorScheme.onSurface else fontGrayColor
                                        )
                                    },
                                )
                            }
                        }
                    }
                ) {
                    key(isDarkTheme) {
                        CurrentTab()
                    }
                }
            }
        }
    }
}



