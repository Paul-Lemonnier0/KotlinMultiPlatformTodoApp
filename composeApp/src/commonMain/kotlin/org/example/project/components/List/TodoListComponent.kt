package org.example.project.components.List

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.components.Card.TodoItemCard
import org.example.project.models.TodoList

/**
 * TodoListSection component
 * @param todoList the todo list to display
 * @param handleToggleTodo action of the checkbox when it is clicked
 */
@Composable
fun TodoListSection(
    todoList: TodoList,
    handleToggleTodo: (id: String, isDone: Boolean) -> Unit
) {

    // Utils for the todo list
    val isItemSelected: (String) -> Boolean = { id -> todoList.isSelected(id) }
    val selectItem: (String) -> Unit = { id -> todoList.handleSelectItem(id) }

    val toggleTodoItem: (String) -> Unit = { id ->
        val isDone = todoList.toggleItemCompletion(id)
        handleToggleTodo(id, isDone)
    }

    // Lazy column to display the todo list (it's optimal for large lists)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        contentPadding = PaddingValues(top = 15.dp)
    ) {
        items(todoList.items) { item ->
            //Using our custom todo item card component
            TodoItemCard(
                item = item,
                selectItem = selectItem,
                isItemSelected = isItemSelected,
                toggleTodoItem = toggleTodoItem
            )
        }
    }
}