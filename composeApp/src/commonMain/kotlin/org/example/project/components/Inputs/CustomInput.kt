package org.example.project.components.Inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import ui.theme.RegularText

import contrastColor
import secondaryColor
import fieldColor
import ui.theme.MyShapes

/**
 * Custom text input component
 * @param text the text of the input
 * @param placeholder the placeholder of the input
 */
@Composable
fun CustomInput(
    text: MutableState<String>,
    placeholder: String
) {
    // State to keep track of the focus state of the input
    val isFocused = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = {
            // If not focused, display the placeholder
            if (!isFocused.value) {
                Text(placeholder, style = RegularText(isGray = true))
            }
        },
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth() // Take all the width available
            .onFocusChanged { focusState ->
                // Update the focus state
                isFocused.value = focusState.isFocused
            },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = contrastColor,
            unfocusedBorderColor = secondaryColor,
            cursorColor = contrastColor,
            focusedLabelColor = secondaryColor,
            unfocusedLabelColor = fieldColor,
        ),

        textStyle = RegularText(), // Calling our prebuild text style
        singleLine = true,
        shape = MyShapes.medium // eq. BorderRadius
    )
}