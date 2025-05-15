import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.App
import org.example.project.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}