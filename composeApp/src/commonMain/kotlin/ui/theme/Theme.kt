import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ui.theme.MyShapes
import ui.theme.WorkSansTypography

/**
 * Light theme base colors
 */
private val LightColorScheme = lightColorScheme(
    primary = primaryColorLight,
    secondary = secondaryColorLight,
    background = Color(0xFF000000),
    surface = fieldColorLight,
    onPrimary = textColorLight,
    onSecondary = textColorContrastLight,
    onBackground = textColorDark,
    onSurface = textColorDark,
)

/**
 * Dark theme base colors
 */
private val DarkColorScheme = darkColorScheme(
    primary = primaryColorDark,
    secondary = secondaryColorDark,
    background = fieldColorDark,
    surface = fieldColorDark,
    onPrimary = textColorDark,
    onSecondary = textColorContrastDark,
    onBackground = textColorLight,
    onSurface = textColorLight,
)

/**
 * Custom theme composable (the wrapper of the app)
 */
@Composable
fun CustomTheme(
    content: @Composable () -> Unit
) {

    val colors = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors, // Use the custom color scheme
        typography = WorkSansTypography(), // Use the custom typography
        shapes = MyShapes, // Use the custom shapes
        content = content // Apply the content (child, eq. App)
    )
}
