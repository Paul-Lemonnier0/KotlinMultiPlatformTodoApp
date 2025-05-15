package org.example.project.models

import com.russhwolf.settings.Settings

class LanguagePreference(
    private val settings: Settings
) {

    private val key = "savedLanguageIso"

    fun saveLanguage(iso: String) {
        settings.putString(key, iso)
    }

    fun loadLanguage(): String? {
        return settings.getStringOrNull(key)
    }
}