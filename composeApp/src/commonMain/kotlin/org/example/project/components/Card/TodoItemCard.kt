package org.example.project.components.Card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.components.Buttons.CustomCheckbox
import org.example.project.models.TodoItem
import ui.theme.RegularText

/**
 * TodoItemCard component (displayed in a todoList)
 * @param item the todo item to display
 * @param selectItem action of the card when it is clicked
 * @param isItemSelected if the card is selected or not
 * @param toggleTodoItem action of the checkbox when it is clicked
 */
@Composable
fun TodoItemCard(
    item: TodoItem,
    selectItem: (String) -> Unit,
    isItemSelected: (String) -> Boolean,
    toggleTodoItem: (String) -> Unit
) {
    // Calling our custom card component
    CardComponent(
        onPress = { selectItem(item.id) },
        isSelected = { isItemSelected(item.id) },
        content = { TodoContent(item, toggleTodoItem) }
    )
}

/**
 * TodoItemContent component: define the content of the todo list item card
 * @param todo the todo item to display
 * @param toggleTodoItem action of the checkbox when it is clicked
 */
@Composable
fun TodoContent(
    todo: TodoItem,
    toggleTodoItem: (String) -> Unit
) {
    Row(
        modifier = Modifier.padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TodoTitle(title = todo.title)

        Spacer(modifier = Modifier.weight(1.0f))

        CustomCheckbox(
            isChecked = todo.isDone,
            onCheckedChange = {
                toggleTodoItem(todo.id)
            }
        )
    }
}

/**
 * TodoTitle component: define the title of the todo list item card
 */
@Composable
fun TodoTitle(
    title: String
) {
    Text(
        text = title,
        style = RegularText() // Calls one of our predefined text style
    )
}