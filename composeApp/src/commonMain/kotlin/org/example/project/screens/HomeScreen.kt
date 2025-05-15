package org.example.project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.components.BottomSheets.AddTodoBottomSheet
import org.example.project.viewmodels.TodoViewModel
import org.example.project.components.List.TodoListSection

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinprojecttest.composeapp.generated.resources.Res
import kotlinprojecttest.composeapp.generated.resources.app_name
import kotlinprojecttest.composeapp.generated.resources.task_details_remove
import org.example.project.components.Buttons.BorderIconButton
import org.example.project.getPlatform
import org.example.project.models.TodoListClient
import ui.theme.HugeText
import ui.theme.RegularText

import org.example.project.layout.BaseScreenContainer

import org.example.project.screens.TodoItemDetails.TodoItemDetailsScreen
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import org.koin.core.parameter.parametersOf

class HomeScreen(): Screen {
    @Composable
    override fun Content() {
        HomeScreenContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent() {
    // The todoList instance
    val scope = rememberCoroutineScope()
    val viewModel: TodoViewModel = getKoin().get()

    val todoList = viewModel.getTodoList()

    // The scope : helping dealing with the state of the app

    // Today date to string
    val today = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString() }

    // The bottom sheet states
    val addSheetState = androidx.compose.material3.rememberModalBottomSheetState()
    val navigator = LocalNavigator.current

    BaseScreenContainer(
        hasBottomPadding = false
    ) {
        Column {
            HeaderSection(today, viewModel::toggleAddModal)
            ProgressSection(todoList.doneRatio())

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                TodoListSection(
                    todoList = todoList,
                    handleItemClick = { id -> navigator?.push(TodoItemDetailsScreen(id)) }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Displaying the add bottom sheet modal if needed
            if (viewModel.showAddBottomSheet.value) {
                AddTodoBottomSheet(
                    showBottomSheet = viewModel.showAddBottomSheet,
                    sheetState = addSheetState,
                    handleValidation = { text -> viewModel.addNewItem(text) }
                )
            }
        }
    }
}


/**
 * Header section of the app
 * Displays the app name, the date and the platform
 */
@Composable
fun HeaderSection(today: String, handleAdd: () -> Unit) {

    val platform = getPlatform().name

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            androidx.compose.material.Text(stringResource(Res.string.app_name), style = HugeText())
            androidx.compose.material.Text(today, style = RegularText(isGray = true))
            androidx.compose.material.Text(platform, style = RegularText())
        }

        BorderIconButton(
            onClick = handleAdd,
            icon = Icons.Default.Add,
            borderColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.onSurface,
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
            color = MaterialTheme.colorScheme.onSurface,
            backgroundColor = MaterialTheme.colorScheme.secondary,
            progress = (progress.coerceIn(0.0, 100.0) / 100).toFloat()
        )
        Text("$progress%", style = RegularText())
    }
}