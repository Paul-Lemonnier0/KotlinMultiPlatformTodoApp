package org.example.project.components.Buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import contrastColor
import textColorContrast
import secondaryColor

import ui.theme.MyShapes

import ui.theme.SubTitleText

/**
 * Base button defined in order to reduce code duplication and creates derivatives that will be used
 * @param text text of the button
 * @param onClick action of the button
 * @param icon icon of the button
 * @param backgroundColor background color of the button
 * @param contentColor content color of the button
 * @param borderColor border color of the button
 * @param disabled if the button is disabled or not
 */
@Composable
fun BaseButton(
    text: String? = null,
    onClick: () -> Unit,
    icon: ImageVector? = null,
    backgroundColor: Color,
    contentColor: Color,
    borderColor: Color? = null,
    disabled: Boolean = false
) {
    // Type of the created button
    val isTextButton = text != null
    val isTextAndIconButton = isTextButton && icon != null

    // Modifier of the button depending on the button type
    val buttonStretchModifier = if (isTextButton) Modifier.fillMaxWidth() else Modifier

    // Colors of the button depending on the button type
    val bdColor = if (disabled) secondaryColor else borderColor ?: backgroundColor
    val tintColor = if (disabled) secondaryColor else contentColor

    // Arrangement of the button depending on the button type
    val rowArrangement = when {
        isTextAndIconButton -> Arrangement.Start
        else -> Arrangement.Center
    }

    // Modifier of the button depending on the button type
    val rowModifier = when {
        isTextAndIconButton -> Modifier.fillMaxWidth()
        else -> Modifier
    }
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = tintColor
        ),
        contentPadding = PaddingValues(15.dp),
        border = BorderStroke(2.dp, bdColor),
        shape = MyShapes.large, // eq. BorderRadius
        modifier = Modifier // Merge of the defined styles earlier in the code
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .then(buttonStretchModifier)
    ) {
        Row(
            modifier = rowModifier,
            horizontalArrangement = rowArrangement,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // If there is a text, we display it
            if (text != null) {
                Text(
                    text = text,
                    color = tintColor,
                    style = SubTitleText(),
                    modifier = Modifier.padding(0.dp)
                )
            }

            // If there is a text and an icon, there is the need to have a spacer between those two
            if(isTextAndIconButton) {
                Spacer(modifier = Modifier.weight(1.0f))
            }

            // If there is an icon, we display it
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = tintColor,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
    }
}

/**
 * Base BackgroundTextButton, build from BaseButton
 * @param onClick action of the button
 * @param text text of the button
 * @param trailingIcon icon of the button
 * @param backgroundColor background color of the button
 * @param contentColor content color of the button
 * @param disabled if the button is disabled or not
 */
@Composable
fun BackgroundTextButton(
    onClick: () -> Unit,
    text: String,
    trailingIcon: ImageVector? = null,
    backgroundColor: Color? = null,
    contentColor: Color? = null,
    disabled: Boolean = false
) {
    BaseButton(
        onClick = onClick,
        text = text,
        icon = trailingIcon,
        backgroundColor = backgroundColor ?: contrastColor,
        contentColor = contentColor ?: textColorContrast,
        disabled = disabled
    )
}

/**
 * Base BorderIconButton, build from BaseButton
 * @param onClick action of the button
 * @param icon icon of the button
 * @param borderColor border color of the button
 * @param contentColor content color of the button
 * @param disabled if the button is disabled or not
 */
@Composable
fun BorderIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    borderColor: Color?,
    contentColor: Color?,
    disabled: Boolean = false
) {
    BaseButton(
        text = null,
        onClick = onClick,
        icon = icon,
        backgroundColor = Color.Transparent,
        contentColor = contentColor ?: contrastColor,
        borderColor = borderColor ?: contrastColor,
        disabled = disabled
    )
}


