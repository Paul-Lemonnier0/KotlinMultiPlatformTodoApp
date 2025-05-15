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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinprojecttest.composeapp.generated.resources.Res
import kotlinprojecttest.composeapp.generated.resources.edit_todo_edit
import kotlinprojecttest.composeapp.generated.resources.edit_todo_title
import kotlinprojecttest.composeapp.generated.resources.edit_todo_title_placeholder
import kotlinprojecttest.composeapp.generated.resources.task_details_remove
import kotlinx.coroutines.launch
import org.example.project.components.Inputs.CustomInput
import org.example.project.models.TodoItem
import org.jetbrains.compose.resources.stringResource

/**
 * Bottom sheet for editing a todo item
 * @param showBottomSheet handle show bottom sheet
 * @param sheetState the state of the bottom sheet (open, closed, ...)
 * @param handleEdit handle the validation of the bottom sheet
 * @param todo the todo item to edit
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditTodoBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    handleEdit: (String) -> Unit,
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
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(showBottomSheet.value) {
        if (showBottomSheet.value) {
            coroutineScope.launch { sheetState.show() }
        } else {
            coroutineScope.launch { sheetState.hide() }
        }
    }

    if (sheetState.isVisible) {
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
                BottomSheetTitle(stringResource(Res.string.edit_todo_title))

                // Input field for the todo item title
                CustomInput(
                    text = text,
                    placeholder = stringResource(Res.string.edit_todo_title_placeholder)
                )

                // Footer of the bottom sheet (edit and remove button)
                BottomSheetFooter(
                    baseButtonTitle = stringResource(Res.string.edit_todo_edit),
                    baseButtonAction = { validate() },
                    baseButtonIcon = Icons.Filled.KeyboardArrowUp,
                )
            }
        }
    }
}