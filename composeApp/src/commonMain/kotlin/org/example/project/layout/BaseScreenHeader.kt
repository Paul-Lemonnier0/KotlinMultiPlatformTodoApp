package org.example.project.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.components.Buttons.GoBackNavigationButton
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.HugeText
import ui.theme.TitleText

/**
 * Base screen header, used to display the base header of a screen (title, leftButton ?, rightButton ?)
 */
@Preview
@Composable
fun BaseScreenHeader(
    title: String,
    leftButton: (@Composable () -> Unit)? = null,
    rightButton: (@Composable () -> Unit)? = null,
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            leftButton?.invoke()

            Box(modifier = Modifier.weight(1f))

            rightButton?.invoke() ?: Spacer(modifier = Modifier.width(24.dp))
        }


        Text(
            text = title,
            style = TitleText()
        )
    }
}