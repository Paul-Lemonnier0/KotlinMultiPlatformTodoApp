package org.example.project.models

import kotlinx.serialization.Serializable

/**
 * TodoList model
 */
@Serializable
data class TodoItem (
    val id: String, // Unique identifier for the todo item
    val title: String, // Title of the todo item
    var isDone: Boolean = false, // Whether the todo item is completed or not
    val creationDate: String // Date when the todo item was created
)