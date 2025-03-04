package org.example.project.components.BottomSheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.components.Inputs.CustomInput

/**
 * Bottom sheet for adding a new todo item
 * @param showBottomSheet handle show bottom sheet
 * @param sheetState the state of the bottom sheet (open, closed, ...)
 * @param handleValidation handle the validation of the bottom sheet
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddTodoBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    handleValidation: (String) -> Unit
) {

    // the title of the todo item that will be added
    val text = remember { mutableStateOf("") }

    // validation logic
    fun validate() {
        // handle validation with the input text value
        handleValidation(text.value)

        // reset the text value
        text.value = ""

        // hide the bottom sheet
        showBottomSheet.value = false
    }

    // bottom sheet content
    ModalBottomSheet(
        onDismissRequest = {
            // Hide the bottom sheet when the user clicks outside of it
            showBottomSheet.value = false
        },
        sheetState = sheetState // the state of the bottom sheet
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {

            // bottom sheet title
            BottomSheetTitle("Ajouter une tâche")

            // input field for the todo item title
            CustomInput(
                text = text,
                placeholder = "Nom de la tâche"
            )

            // footer of the bottom sheet (add button)
            BottomSheetFooter(
                baseButtonTitle = "Ajouter",
                baseButtonAction = { validate() },
                baseButtonIcon = Icons.Filled.KeyboardArrowUp
            )
        }
    }
}