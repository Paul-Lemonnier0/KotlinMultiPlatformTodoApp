package org.example.project.components.Buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ui.theme.RegularText

import org.example.project.components.Card.CardComponent
import fontGrayColor

/**
 * Custom radio button component displaying an image and a text
 * @param onClick action of the radio button when it is clicked
 * @param isSelected if the radio button is selected or not
 * @param img image of the radio button
 * @param label text of the radio button
 * @param iconSize size of the icon
 */
@Composable
fun ImageRadioButton(
    onClick: () -> Unit,
    isSelected: Boolean,
    img: Painter,
    label: String? = null,
    iconSize: Dp = 24.dp
) {
    CardComponent(
        onPress = { onClick() },
        isSelected = { isSelected }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Image(
                painter = img,
                contentDescription = label,
                modifier = Modifier.size(iconSize)
            )

            label?.let {
                Text(
                    text = it,
                    style = RegularText(isGray = !isSelected)
                )
            }
        }
    }
}
