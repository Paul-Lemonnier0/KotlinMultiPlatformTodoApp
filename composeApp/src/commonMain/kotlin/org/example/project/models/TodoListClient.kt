package org.example.project.models

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * TodoListClient model
 * A service to handle the API interactions
 */
class TodoListClient(private val httpClient: HttpClient) {

    // The base URL of the API
    private val baseURL = "http://10.0.2.2:8080";

    // Base Headers and params
    private fun io.ktor.client.request.HttpRequestBuilder.setCommonHeaders() {
        headers {
            append("Accept", "application/json")
            append("Content-Type", "application/json")
        }
    }

    /**
     * Fetches the todo items from the API
     * @return a list of todo items
     */
    suspend fun fetchTodoItems(): List<TodoItem> {
        val response: HttpResponse = try {
            httpClient.get("$baseURL/list") {
                setCommonHeaders()
            }
        } catch (e: UnresolvedAddressException) {
            println("Unable to resolve address : $e")
            return emptyList()
        } catch (e: Exception) {
            println("Unexpected exception $e")
            return emptyList()
        }

        return handleResponse(response)
    }

    /**
     * Function helping to reduce code duplications by centralizing the response handling
     * @param response the http response
     */
    private suspend fun handleResponse(response: HttpResponse): List<TodoItem> {
        return when (response.status) {
            HttpStatusCode.OK -> {
                try {
                    response.body<List<TodoItem>>()
                } catch (e: SerializationException) {
                    println("Failed to deserialize response $e")
                    emptyList()
                }
            }
            else -> {
                println("Unexpected response status: ${response.status.value}")
                emptyList()
            }
        }
    }

    /**
     * Adds a todo item to the API
     * @param item the todo item to add
     */
    suspend fun addTodoItem(item: TodoItem): HttpResponse {
        return httpClient.post("$baseURL/add") {
            setCommonHeaders()

            val id = item.id;
            val title = item.title;

            setBody(Json.encodeToString(mapOf("id" to id, "title" to title)))
        }
    }

    /**
     * Edits a todo item in the API
     * @param id the id of the todo item to edit
     * @param newTitle the new title of the todo item
     */
    suspend fun editTodoItem(id: String, newTitle: String): HttpResponse {
        return httpClient.post("$baseURL/updateTitle") {
            setCommonHeaders()
            setBody(Json.encodeToString(mapOf("id" to id, "title" to newTitle)))
        }
    }

    /**
     * Removes a todo item from the API
     * @param id the id of the todo item to remove
     */
    suspend fun removeTodoItem(id: String): HttpResponse {
        return httpClient.post("$baseURL/delete") {
            setCommonHeaders()
            setBody(Json.encodeToString(mapOf("id" to id)))
        }
    }

    /**
     * Toggles the completion status of a todo item in the API
     * @param id the id of the todo item to toggle
     * @param isDone the new completion status of the todo item
     */
    suspend fun toggleTodoItem(id: String, isDone: Boolean): HttpResponse {
        return httpClient.post("$baseURL/update") {
            setCommonHeaders()
            setBody(Json.encodeToString(mapOf("id" to id, "isDone" to isDone)))

        }
    }
}