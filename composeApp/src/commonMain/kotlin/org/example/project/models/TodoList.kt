package org.example.project.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlin.math.round

/**
 * TodoList model
 */
class TodoList {

    // List of todo items (mutable so the updates can be reflected in the UI)
    val items = mutableStateListOf<TodoItem>()

    // Selected item (mutable so the updates can be reflected in the UI)
    private var selectedItem: MutableState<String?> = mutableStateOf(null)

    // Utils for the todo list

    /** Returns true if the item is selected
     * @param id the id of the item
     */
    fun isSelected(id: String): Boolean = selectedItem.value == id

    /**
     * Returns true if a todo item is selected
     */
    fun hasSelectedItem(): Boolean = selectedItem.value != null

    /**
     * Selects an item
     * @param id the id of the item
     */
    fun handleSelectItem(id: String) {
        selectedItem.value = if (selectedItem.value == id) null else id
    }

    /**
     * Returns the selected item
     */
    fun getSelectedItem(): TodoItem? {
        return selectedItem.value?.let { getTodoByID(it) }
    }

    /**
     * Adds an item to the list
     * @param item the item to add
     */
    fun addItem(item: TodoItem) {
        items.add(item)
    }

    /**
     * Removes an item from the list
     * @param id the id of the item
     */
    fun removeItem(id: String) {
        items.removeAll { it.id == id }
    }

    /**
     * Edits an item in the list
     * @param id the id of the item
     * @param title the new title of the item
     */
    fun editItem(id: String, title: String) {
        val index = items.indexOfFirst { it.id == id }
        if (index != -1) {
            items[index] = items[index].copy(title = title)
        }
    }

    /**
     * Returns the item with the given id
     * @param id the id of the item
     */
    private fun getTodoByID(id: String): TodoItem? {
        val todo = items.find { it.id == id }
        return todo;
    }

    /**
     * Toggles the completion status of an item
     * @param id the id of the item
     */
    fun toggleItemCompletion(id: String): Boolean {
        val todo = getTodoByID(id);

        todo?.let {
            val index = items.indexOf(it)
            items[index] = it.copy(isDone = !it.isDone)
            return items[index].isDone
        }

        return false
    }

    /**
     * Returns the ratio of completed items
     */
    fun doneRatio(): Double {
        val doneCount = items.count { it.isDone }
        val totalCount = items.size
        return if (totalCount > 0) {
            val ratio = (doneCount.toDouble() / totalCount * 100)
            round(ratio * 10) / 10
        } else {
            0.0
        }
    }
}
