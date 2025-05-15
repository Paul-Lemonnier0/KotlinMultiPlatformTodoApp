package org.example.project.viewmodels

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.project.models.TodoItem
import org.example.project.models.TodoList
import org.example.project.models.TodoListClient
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * TodoViewModel model
 * Helps separating the logic from the UI
 * @param client the todo list client
 * @param todoList the todo list
 */
@OptIn(ExperimentalUuidApi::class)
class TodoViewModel(private val client: TodoListClient, private val todoList: TodoList) {

    // The base bottom sheets states
    val showAddBottomSheet = mutableStateOf(false)
    val showEditBottomSheet = mutableStateOf(false)
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    // On init we fetch the items
    init {
        fetchItems()
    }

    fun getTodoList(): TodoList {
        return this.todoList
    }

    fun getTodoItem(id: String): TodoItem? {
        return this.todoList.items.find { it.id == id }
    }

    /**
     * Item fetching function in the scope (coroutine)
     */
    private fun fetchItems() {
        scope.launch {
            try {
                val list: List<TodoItem> = client.fetchTodoItems()
                todoList.items.addAll(list)
            } catch (e: Throwable) {
                println("Error fetching todo items: ${e.message}")
            }
        }
    }

    /**
     * Add a new item internally and in the API
     * @param text the text of the new item
     */
    fun addNewItem(text: String) {
        scope.launch {
            try {
                val newItem = TodoItem(
                    id = Uuid.random().toString(),
                    title = text,
                    creationDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toString()
                )
                client.addTodoItem(newItem)
                todoList.addItem(newItem)
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    /**
     * Edit an item internally and in the API
     * @param id the id of the item to edit
     * @param newTitle the new title of the item
     */
    fun editItem(id: String, newTitle: String) {
        scope.launch {
            try {
                client.editTodoItem(id, newTitle)
                todoList.editItem(id, newTitle)
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    /**
     * Remove an item internally and in the API
     * @param id the id of the item to remove
     */
    fun removeItem(id: String) {
        scope.launch {
            try {
                client.removeTodoItem(id)
                todoList.removeItem(id)
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    /**
     * Toggle an item internally and in the API
     * @param id the id of the item to toggle
     * @param isDone the new completion status of the item
     */
    fun toggleTodoItem(id: String, isDone: Boolean) {
        scope.launch {
            try {
                todoList.toggleItemCompletion(id)
                client.toggleTodoItem(id, isDone)
            } catch (e: Throwable) {
                println("Error: ${e.message}")
            }
        }
    }

    /**
     * Toggle the add bottom sheet modal
     */
    fun toggleAddModal() {
        showAddBottomSheet.value = !showAddBottomSheet.value
    }

    /**
     * Toggle the edit bottom sheet modal
     */
    fun toggleEditModal() {
        showEditBottomSheet.value = !showEditBottomSheet.value
    }
}
