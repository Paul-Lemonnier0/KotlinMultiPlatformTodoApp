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
    handleItemClick: (id: String) -> Unit
) {
    // Lazy column to display the todo list (it's optimal for large lists)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        contentPadding = PaddingValues(top = 15.dp, bottom = 30.dp)
    ) {
        items(todoList.items) { item ->
            //Using our custom todo item card component
            TodoItemCard(
                item = item,
                onClick = handleItemClick
            )
        }
    }
}