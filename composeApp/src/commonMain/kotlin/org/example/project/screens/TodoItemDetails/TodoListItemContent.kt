package org.example.project.screens.TodoItemDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.components.Buttons.CustomCheckbox
import org.example.project.models.TodoItem
import org.example.project.utils.getTodoDateString
import org.example.project.viewModels.LanguageViewModel
import org.example.project.viewmodels.TodoViewModel
import org.koin.compose.getKoin
import ui.theme.RegularText
import ui.theme.TitleText

@Composable
fun TodoListItemContent(todo: TodoItem) {
    val todoCreationDateTime = Instant.parse(todo.creationDate)
    val todoCreationDate = todoCreationDateTime.toLocalDateTime(TimeZone.currentSystemDefault())

    val languageViewModel = getKoin().get<LanguageViewModel>()
    val language = languageViewModel.selectedLanguage
    val creationDateString = getTodoDateString(todoCreationDate, language.value.iso)

    val viewModel = getKoin().get<TodoViewModel>()

    Box {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                androidx.compose.material.Text(todo.title, style = TitleText())
                androidx.compose.material.Text(creationDateString, style = RegularText())
            }

            CustomCheckbox(
                isChecked = todo.done,
                onCheckedChange = { viewModel.toggleTodoItem(todo.id, !todo.done) }
            )
        }

    }
}