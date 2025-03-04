package ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fontGrayColor
import kotlinprojecttest.composeapp.generated.resources.Res
import kotlinprojecttest.composeapp.generated.resources.worksans_Light
import kotlinprojecttest.composeapp.generated.resources.worksans_Regular
import kotlinprojecttest.composeapp.generated.resources.worksans_bold
import kotlinprojecttest.composeapp.generated.resources.worksans_medium
import kotlinprojecttest.composeapp.generated.resources.worksans_semibold
import org.jetbrains.compose.resources.Font
import textColor
import textColorContrast


/**
 * Imports of a custom font family
 */
@Composable
fun WorkSansFontFamily() = FontFamily(
    Font(Res.font.worksans_Light, weight = FontWeight.Light),
    Font(Res.font.worksans_Regular, weight = FontWeight.Normal),
    Font(Res.font.worksans_medium, weight = FontWeight.Medium),
    Font(Res.font.worksans_semibold, weight = FontWeight.SemiBold),
    Font(Res.font.worksans_bold, weight = FontWeight.Bold)
)

/**
 * Definition of the custom typography for the hole app
 */
@Composable
fun WorkSansTypography() = Typography().run {

    val fontFamily = WorkSansFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily =  fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}

/**
 * Definition of the custom text styles for the hole app
 */

/**
 * Regular text style (the base one in the app)
 */
@Composable
fun RegularText(isGray: Boolean = false, isContrast: Boolean = false) = TextStyle(
    fontFamily = WorkSansFontFamily(),
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    color = if (isGray) fontGrayColor else if (isContrast) textColorContrast else textColor
)

/**
 * Sub title text style
 */
@Composable
fun SubTitleText(isGray: Boolean = false, isContrast: Boolean = false) = TextStyle(
    fontFamily = WorkSansFontFamily(),
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    color = if (isGray) fontGrayColor else if (isContrast) textColorContrast else textColor
)

/**
 * Title text style
 */
@Composable
fun TitleText(isGray: Boolean = false, isContrast: Boolean = false) = TextStyle(
    fontFamily = WorkSansFontFamily(),
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp,
    color = if (isGray) fontGrayColor else if (isContrast) textColorContrast else textColor
)

/**
 * Huge text style
 */
@Composable
fun HugeText(isGray: Boolean = false, isContrast: Boolean = false) = TextStyle(
    fontFamily = WorkSansFontFamily(),
    fontWeight = FontWeight.Bold,
    fontSize = 40.sp,
    color = if (isGray) fontGrayColor else if (isContrast) textColorContrast else textColor
)
