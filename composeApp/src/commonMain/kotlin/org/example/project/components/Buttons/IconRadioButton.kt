package org.example.project.components.Buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ui.theme.RegularText

import org.example.project.components.Card.CardComponent
import fontGrayColor


/**
 * Custom radio button component displaying an icon and a text
 * @param onClick action of the radio button when it is clicked
 * @param isSelected if the radio button is selected or not
 * @param icon icon of the radio button
 * @param label text of the radio button
 * @param iconSize size of the icon
 */
@Composable
fun IconRadioButton(
    onClick: () -> Unit,
    isSelected: Boolean,
    icon: ImageVector,
    label: String? = null,
    iconSize: Dp = 24.dp
) {
    //Color of the icon whether it is selected or not
    val highlightColor = if(isSelected) MaterialTheme.colorScheme.onSurface else fontGrayColor

    CardComponent(
        onPress = { onClick() },
        isSelected = { isSelected }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = highlightColor,
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
