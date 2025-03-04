package org.example.project

import CustomTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.saveable.rememberSaveable

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.models.TodoList
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.HugeText
import ui.theme.RegularText

import contrastColor
import secondaryColor

import kotlinx.coroutines.launch
import org.example.project.components.BottomSheets.AddTodoBottomSheet
import org.example.project.components.BottomSheets.EditTodoBottomSheet
import org.example.project.components.Buttons.BackgroundTextButton
import org.example.project.components.Buttons.BorderIconButton
import org.example.project.components.List.TodoListSection
import org.example.project.models.TodoListClient
import org.example.project.viewmodels.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(
    client: TodoListClient
) {
    // The todoList instance
    val todoList = TodoList()

    // The scope : helping dealing with the state of the app
    val scope = rememberCoroutineScope()

    // The viewModel instance
    val viewModel = remember { TodoViewModel(client, scope, todoList) }

    // Today date to string
    val today = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString() }

    // The bottom sheet states
    val addSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    val editSheetState = androidx.compose.material3.rememberModalBottomSheetState()

    // Using the CustomTheme as a wrapper so our components can be styled
    CustomTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            ) {
                HeaderSection(today, todoList.hasSelectedItem(), viewModel::toggleEditModal)
                ProgressSection(todoList.doneRatio())

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    TodoListSection(todoList) { id: String, isDone: Boolean ->
                        viewModel.toggleTodoItem(
                            id,
                            isDone
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                BackgroundTextButton(
                    onClick = viewModel::toggleAddModal,
                    text = "Ajouter",
                )

                // Displaying the add bottom sheet modal if needed
                if (viewModel.showAddBottomSheet.value) {
                    AddTodoBottomSheet(
                        showBottomSheet = viewModel.showAddBottomSheet,
                        sheetState = addSheetState,
                        handleValidation = { text -> viewModel.addNewItem(text) }
                    )
                }

                // Displaying the settings bottom sheet modal if needed
                if(viewModel.showEditBottomSheet.value) {
                    val selectedTask = todoList.getSelectedItem()

                    // Double check if the selected task is not null
                    if (selectedTask != null) {
                        EditTodoBottomSheet(
                            todo = selectedTask,
                            sheetState = editSheetState,
                            showBottomSheet = viewModel.showEditBottomSheet,
                            handleEdit = ({ text -> viewModel.editItem(selectedTask.id, text) }),
                            handleDelete = { scope.launch { viewModel.removeItem(selectedTask.id) } }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Header section of the app
 * Displays the app name, the date and the platform
 */
@Composable
fun HeaderSection(today: String, hasSelectedItem: Boolean, handleEdit: () -> Unit) {

    val platform = getPlatform().name

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text("todo.", style = HugeText())
            Text(today, style = RegularText(isGray = true))
            Text(platform, style = RegularText())
        }

        BorderIconButton(
            onClick = handleEdit,
            icon = Icons.Default.Edit,
            borderColor = contrastColor,
            contentColor = contrastColor,
            disabled = !hasSelectedItem
        )
    }
}

/**
 * Progress section of the app
 * Displays the progress of the tasks
 */
@Composable
fun ProgressSection(progress: Double = 0.0) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp, bottom = 10.dp),
            strokeCap = StrokeCap.Round,
            color = contrastColor,
            backgroundColor = secondaryColor,
            progress = (progress.coerceIn(0.0, 100.0) / 100).toFloat()
        )
        Text("$progress%", style = RegularText())
    }
}