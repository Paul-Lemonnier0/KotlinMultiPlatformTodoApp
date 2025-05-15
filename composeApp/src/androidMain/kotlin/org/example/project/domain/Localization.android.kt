package org.example.project.domain

import android.content.Context
import java.util.Locale

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Localization(
    private val context: Context
) {
    actual fun applyLanguages(iso: String) {
        val locale = Locale(iso)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
    }
}