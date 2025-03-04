package org.example.project.components.BottomSheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import org.example.project.models.TodoItem

/**
 * Bottom sheet for editing a todo item
 * @param showBottomSheet handle show bottom sheet
 * @param sheetState the state of the bottom sheet (open, closed, ...)
 * @param handleEdit handle the validation of the bottom sheet
 * @param handleDelete handle the deletion of the bottom sheet
 * @param todo the todo item to edit
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditTodoBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    handleEdit: (String) -> Unit,
    handleDelete: () -> Unit,
    todo: TodoItem
) {

    // The title of the todo to edit
    val text = remember { mutableStateOf(todo.title) }

    // Closing modal
    fun closeModal() {
        // Reset the input value
        text.value = ""

        // Close the modal
        showBottomSheet.value = false
    }

    // Handle submission
    fun validate() {
        // Handle validation with the input text value
        handleEdit(text.value)

        closeModal()
    }

    fun delete() {
        handleDelete()
        closeModal()
    }

    ModalBottomSheet(
        onDismissRequest = {
            // Hide the bottom sheet when the user clicks outside of it
            showBottomSheet.value = false
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {

            // Bottom sheet title
            BottomSheetTitle("Modifier une tâche")

            // Input field for the todo item title
            CustomInput(
                text = text,
                placeholder = "Nom de la tâche"
            )

            // Footer of the bottom sheet (edit and remove button)
            BottomSheetFooter(
                baseButtonTitle = "Modifier",
                baseButtonAction = { validate() },
                baseButtonIcon = Icons.Filled.KeyboardArrowUp,

                redButtonTitle = "Supprimer",
                redButtonAction = { delete() },
                redButtonIcon = Icons.Filled.Delete
            )
        }
    }
}