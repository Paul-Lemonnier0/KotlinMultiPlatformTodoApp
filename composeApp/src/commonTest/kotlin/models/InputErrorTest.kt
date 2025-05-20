import androidx.compose.runtime.mutableStateOf
import org.example.project.models.InputError
import kotlin.test.*

class ValidationTests {
    @Test
    fun testCorrectMessage() {
        val inputError = InputError(mutableStateOf(false), "Test du message d'erreur")
        assertTrue(inputError.getErrorMessage() == "Test du message d'erreur")
    }

    @Test
    fun testCorrectErrorValue() {
        val inputError = InputError(mutableStateOf(false), "Test du message d'erreur")
        inputError.toggleHasError(true);
        assertTrue(inputError.hasError());
    }
}
