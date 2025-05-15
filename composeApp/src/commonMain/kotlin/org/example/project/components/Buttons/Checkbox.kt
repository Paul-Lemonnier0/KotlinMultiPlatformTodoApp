package org.example.project.components.Buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Base custom checkbox component
 * @param isChecked if the checkbox is checked or not
 * @param onCheckedChange action of the checkbox when it is clicked
 */
@Composable
fun CustomCheckbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    // Colors of the checkbox depending on its state
    val backgroundColor = if (isChecked) MaterialTheme.colorScheme.onSurface else Color.Transparent
    val borderColor = if (isChecked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.secondary

    // Base radius of the checkbox
    val borderRadius = 500

    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(RoundedCornerShape(borderRadius.dp))
            .background(backgroundColor)
            .border(2.dp, borderColor, RoundedCornerShape(borderRadius.dp))
            .clickable { onCheckedChange(!isChecked) },
        contentAlignment = Alignment.Center
    ) {
        // Displaying the check icon only if the checkbox is checked
        if (isChecked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Checked",
                tint = MaterialTheme.colorScheme.inverseOnSurface,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}