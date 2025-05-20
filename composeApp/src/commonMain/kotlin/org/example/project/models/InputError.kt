package org.example.project.models

import androidx.compose.runtime.MutableState

/**
 * Class used to handle the error state of an input field.
 */
class InputError(
    private val hasError: MutableState<Boolean>,
    private val errorMessage: String
) {
    fun toggleHasError(hasError: Boolean) {
        this.hasError.value = hasError
    }

    fun getErrorMessage(): String {
        return errorMessage
    }

    fun hasError(): Boolean {
        return hasError.value
    }
}
