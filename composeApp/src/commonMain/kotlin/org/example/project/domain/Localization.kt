package org.example.project.domain

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Localization {
    fun applyLanguages(iso: String)
}