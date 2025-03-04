package org.example.project.components.BottomSheets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.TitleText

/**
 * Bottom sheet title
 * @param title the title of the bottom sheet
 */
@Composable
fun BottomSheetTitle(
    title: String
) {
    Text(
        title,
        style = TitleText(), // Use of a predefined text style
        modifier = Modifier.padding(bottom = 10.dp)
    )
}