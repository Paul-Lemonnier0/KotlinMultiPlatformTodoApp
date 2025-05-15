package org.example.project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinprojecttest.composeapp.generated.resources.Res
import kotlinprojecttest.composeapp.generated.resources.en
import kotlinprojecttest.composeapp.generated.resources.fr
import kotlinprojecttest.composeapp.generated.resources.settings_language_category_title
import kotlinprojecttest.composeapp.generated.resources.settings_language_en
import kotlinprojecttest.composeapp.generated.resources.settings_language_fr
import kotlinprojecttest.composeapp.generated.resources.settings_theme_category_title
import kotlinprojecttest.composeapp.generated.resources.settings_theme_dark
import kotlinprojecttest.composeapp.generated.resources.settings_theme_light
import kotlinprojecttest.composeapp.generated.resources.settings_title
import org.example.project.components.Buttons.IconRadioButton
import org.example.project.components.Buttons.ImageRadioButton
import org.example.project.domain.Language
import org.example.project.layout.BaseScreenContainer
import org.example.project.layout.BaseScreenHeader
import org.example.project.viewModels.LanguageViewModel
import org.example.project.viewModels.ThemeViewModel
import org.koin.compose.getKoin
import ui.theme.HugeText
import ui.theme.TitleText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

class SettingsScreen(): Screen {
    @Composable
    override fun Content() {
        SettingsScreenContent()
    }
}

@Composable
fun SettingsScreenContent() {
    val themeViewModel = getKoin().get<ThemeViewModel>()
    val languageViewModel = getKoin().get<LanguageViewModel>()

    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

    val enFlag = painterResource(Res.drawable.en)
    val frFlag = painterResource(Res.drawable.fr)

    val selectedLanguage by languageViewModel.selectedLanguage.collectAsState()

    BaseScreenContainer(
        hasBottomPadding = false
    ) {
        Column {

            Text(text = stringResource(Res.string.settings_title), style = HugeText())
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(Res.string.settings_theme_category_title), style = TitleText())
            Spacer(modifier = Modifier.height(10.dp))

            IconRadioButton(
                onClick = { themeViewModel.toggleTheme() },
                isSelected = isDarkTheme,
                icon = Icons.Filled.DarkMode,
                label = stringResource(Res.string.settings_theme_dark)
            )

            IconRadioButton(
                onClick = { themeViewModel.toggleTheme() },
                isSelected = !isDarkTheme,
                icon = Icons.Default.LightMode,
                label = stringResource(Res.string.settings_theme_light)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(Res.string.settings_language_category_title), style = TitleText())
            Spacer(modifier = Modifier.height(10.dp))

            ImageRadioButton(
                onClick = { languageViewModel.setLanguage(Language.English) },
                isSelected = selectedLanguage == Language.English,
                img = enFlag,
                label = stringResource(Res.string.settings_language_en)
            )

            ImageRadioButton(
                onClick = { languageViewModel.setLanguage(Language.French) },
                isSelected = selectedLanguage == Language.French,
                img = frFlag,
                label = stringResource(Res.string.settings_language_fr)
            )
        }
    }
}
