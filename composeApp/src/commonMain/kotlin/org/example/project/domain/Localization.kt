package org.example.project.domain

/**
 * Localization class to manage the current language of the app
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Localization {
    fun applyLanguages(iso: String)
}