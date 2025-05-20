package org.example.project.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.example.project.domain.Language
import org.example.project.domain.Localization

/**
 * ViewModel for the language (manager)
 */
class LanguageViewModel(
    private val localization: Localization,
) : ViewModel() {
    //Base value of the language
    private val _selectedLanguage = MutableStateFlow(Language.French)
    val selectedLanguage: StateFlow<Language> = _selectedLanguage

    //"Key" to update the UI of the app
    private val _shouldRecreate = MutableStateFlow(false)
    val shouldRecreate: StateFlow<Boolean> = _shouldRecreate

    fun setLanguage(language: Language) {
        _selectedLanguage.value = language
        localization.applyLanguages(language.iso)
        _shouldRecreate.value = true
    }

    fun clearRecreateFlag() {
        _shouldRecreate.value = false
    }

    init {
        val initialLang = Language.French
        _selectedLanguage.value = initialLang
        localization.applyLanguages(initialLang.iso)
    }
}
