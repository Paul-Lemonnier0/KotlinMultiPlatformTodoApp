package org.example.project.components.BottomSheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.example.project.components.Buttons.BackgroundTextButton

/**
 * Default footer of a bottom sheet
 * @param baseButtonTitle title of the base button
 * @param baseButtonAction action of the base button
 * @param baseButtonIcon icon of the base button
 *
 * @param redButtonTitle title of the red button
 * @param redButtonAction action of the red button
 * @param redButtonIcon icon of the red button
 */
@Composable
fun BottomSheetFooter(
    baseButtonTitle: String,
    baseButtonAction: () -> Unit,
    baseButtonIcon: ImageVector? = null,

    redButtonTitle: String? = null,
    redButtonAction: (() -> Unit)? = null,
    redButtonIcon: ImageVector? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp), // Add padding to the top and stretch horizontally
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Gap of 16 dp
    ) {
        // If a red button is provided, display it on the left
        if (redButtonTitle != null && redButtonAction != null) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                BackgroundTextButton(
                    text = redButtonTitle,
                    onClick = redButtonAction,
                    trailingIcon = redButtonIcon,
                    backgroundColor = Color.Red
                )
            }
        }

        // Display the base button on the right (or in the center if no red button)
        Box(
            modifier = Modifier.weight(1f)
        ) {
            BackgroundTextButton(
                text = baseButtonTitle,
                onClick = baseButtonAction,
                trailingIcon = baseButtonIcon,
            )
        }
    }
}