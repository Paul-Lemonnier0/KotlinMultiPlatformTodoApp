package org.example.project.screens.TodoItemDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinprojecttest.composeapp.generated.resources.Res
import kotlinprojecttest.composeapp.generated.resources.task_details_remove
import kotlinprojecttest.composeapp.generated.resources.task_details_title
import org.example.project.components.Buttons.BackgroundTextButton
import org.example.project.layout.BaseScreenContainer
import org.example.project.layout.BaseScreenHeader

import org.example.project.components.BottomSheets.EditTodoBottomSheet

import org.example.project.components.Buttons.BorderIconButton
import org.example.project.components.Buttons.GoBackNavigationButton
import org.example.project.viewmodels.TodoViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin

data class TodoItemDetailsScreen(val todoId: String) : Screen {
    @Composable
    override fun Content() {
        // Use the todoId to fetch the item or pass the whole object if available
        TodoItemDetails(todoId = todoId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemDetails(todoId: String) {
    val viewModel: TodoViewModel = getKoin().get()
    val todoItem = viewModel.getTodoItem(todoId)
    val navigator = LocalNavigator.current
    val editSheetState = androidx.compose.material3.rememberModalBottomSheetState()

    BaseScreenContainer {
        Column(modifier = Modifier.fillMaxSize()) {

            // Header
            BaseScreenHeader(
                title = stringResource(Res.string.task_details_title),
                leftButton = { GoBackNavigationButton() },
                rightButton = {
                    BorderIconButton(
                        onClick = { viewModel.toggleEditModal() },
                        icon = Icons.Outlined.Edit,
                        borderColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                }
            )

            // MAIN CONTENT
            if (todoItem != null) {
                Box(
                    modifier = Modifier
                        .weight(1f) // Takes remaining space
                        .fillMaxSize()
                ) {
                    TodoListItemContent(todo = todoItem)
                }
            } else {
                // Optional: placeholder or loading
                Box(modifier = Modifier.weight(1f)) {}
            }

            // Delete button pinned to bottom
            BackgroundTextButton(
                onClick = {
                    if (todoItem != null) {
                        viewModel.removeItem(todoItem.id)
                        navigator?.pop()
                    }
                },
                text = stringResource(Res.string.task_details_remove),
                backgroundColor = MaterialTheme.colorScheme.error
            )
        }

        if(todoItem != null) {
            EditTodoBottomSheet(
                todo = todoItem,
                sheetState = editSheetState,
                showBottomSheet = viewModel.showEditBottomSheet,
                handleEdit = ({ text ->
                    viewModel.editItem(
                        todoItem.id,
                        text
                    )
                }),
            )
        }
    }
}

