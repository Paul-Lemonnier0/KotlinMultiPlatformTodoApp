package org.example.project.components.Buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator

import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun GoBackNavigationButton() {
    val navigator = LocalNavigator.current

    BorderIconButton(
        onClick = { navigator?.pop() },
        icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
        borderColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSurface
    )
}