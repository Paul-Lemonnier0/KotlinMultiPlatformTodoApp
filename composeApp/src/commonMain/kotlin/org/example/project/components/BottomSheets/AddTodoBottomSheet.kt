package org.example.project.components.BottomSheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import kotlinprojecttest.composeapp.generated.resources.add_todo_add
import kotlinprojecttest.composeapp.generated.resources.add_todo_empty_title_error
import kotlinprojecttest.composeapp.generated.resources.add_todo_title
import kotlinprojecttest.composeapp.generated.resources.add_todo_title_placeholder
import kotlinprojecttest.composeapp.generated.resources.task_details_remove
import kotlinx.coroutines.launch
import org.example.project.components.Inputs.CustomInput
import org.example.project.models.InputError
import org.jetbrains.compose.resources.stringResource

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

    val inputError = InputError(mutableStateOf(false), Res.string.add_todo_empty_title_error.toString())

    // validation logic
    fun validate() {
        if(text.value.isEmpty()) {
            inputError.toggleHasError(true)
            return
        }

        inputError.toggleHasError(false)
        // handle validation with the input text value
        handleValidation(text.value)

        // reset the text value
        text.value = ""

        // hide the bottom sheet
        showBottomSheet.value = false
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
            sheetState = sheetState // the state of the bottom sheet
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {

                // bottom sheet title
                BottomSheetTitle(stringResource(Res.string.add_todo_title))

                // input field for the todo item title
                CustomInput(
                    text = text,
                    placeholder = stringResource(Res.string.add_todo_title_placeholder),
                    inputError = inputError
                )

                // footer of the bottom sheet (add button)
                BottomSheetFooter(
                    baseButtonTitle = stringResource(Res.string.add_todo_add),
                    baseButtonAction = { validate() },
                    baseButtonIcon = Icons.Filled.KeyboardArrowUp
                )
            }
        }
    }
}