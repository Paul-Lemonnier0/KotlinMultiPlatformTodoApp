package org.example.project.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.example.project.screens.HomeScreen
import org.example.project.screens.SettingsScreen

object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Menu)
            return TabOptions(index = 0u, title = "Home", icon = icon)
        }

    @Composable
    override fun Content() {
        Navigator(HomeScreen())
    }
}


object SettingsTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Settings)
            return TabOptions(index = 2u, title = "Profile", icon = icon)
        }

    @Composable
    override fun Content() {
        Navigator(SettingsScreen())
    }
}
