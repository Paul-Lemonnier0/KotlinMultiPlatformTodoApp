package org.example.project.components.Card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import contrastColor
import secondaryColor

/**
 * Base card component
 * @param onPress action of the card when it is clicked
 * @param isSelected if the card is selected or not
 * @param content content of the card
 */
@Composable
fun CardComponent(
    onPress: () -> Unit,
    isSelected: () -> Boolean,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable { onPress() },
        elevation = 0.dp,
        shape = RoundedCornerShape(15.dp), // eq. BorderRadius
        border = BorderStroke(
            width = 2.dp,
            color = if (isSelected()) contrastColor else secondaryColor
        )
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp)
        ) {
            content() // Displaying the content giving in parameters
        }
    }
}
