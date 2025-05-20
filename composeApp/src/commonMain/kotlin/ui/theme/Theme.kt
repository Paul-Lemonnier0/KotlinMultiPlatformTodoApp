import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import ui.theme.MyShapes
import ui.theme.WorkSansTypography

/**
 * Light theme base colors
 */
private val LightColorScheme = lightColorScheme(
    primary = primaryColorLight,
    secondary = secondaryColorLight,
    surface = fieldColorLight,
    onPrimary = textColorLight,
    onSecondary = textColorContrastLight,
    onBackground = textColorDark,
    inverseOnSurface = contrastColorDark,
    onSurface = contrastColorLight,
    tertiary = fieldColorLight
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
    inverseOnSurface = contrastColorLight,
    onSurface = contrastColorDark,
    tertiary = fieldColorDark
)

/**
 * Custom theme composable (the wrapper of the app)
 */
@Composable
fun CustomTheme(isDarkTheme: Boolean, content: @Composable () -> Unit) {
    val colors = if (isDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors, // Use the custom color scheme
        typography = WorkSansTypography(), // Use the custom typography
        shapes = MyShapes, // Use the custom shapes
        content = content // Apply the content (child, eq. App)
    )
}
