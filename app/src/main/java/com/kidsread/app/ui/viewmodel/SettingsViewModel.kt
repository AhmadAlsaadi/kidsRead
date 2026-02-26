package com.kidsread.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kidsread.app.data.model.AppSettings
import com.kidsread.app.data.model.DiacriticType
import com.kidsread.app.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel لصفحة الإعدادات
 */
class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    
    private val settingsRepository = SettingsRepository(application)
    
    private val _settings = MutableStateFlow(AppSettings())
    val settings: StateFlow<AppSettings> = _settings.asStateFlow()
    
    init {
        loadSettings()
    }
    
    private fun loadSettings() {
        viewModelScope.launch {
            settingsRepository.settingsFlow.collect { settings ->
                _settings.value = settings
            }
        }
    }
    
    fun updateWordLength(length: Int) {
        viewModelScope.launch {
            settingsRepository.updateWordLength(length)
        }
    }
    
    fun toggleDiacriticType(type: DiacriticType) {
        viewModelScope.launch {
            val currentDiacritics = _settings.value.selectedDiacritics.toMutableSet()
            
            if (currentDiacritics.contains(type)) {
                // لا نسمح بإزالة آخر نوع
                if (currentDiacritics.size > 1) {
                    currentDiacritics.remove(type)
                }
            } else {
                currentDiacritics.add(type)
            }
            
            settingsRepository.updateSelectedDiacritics(currentDiacritics)
        }
    }
    
    fun updateWordsPerSession(count: Int) {
        viewModelScope.launch {
            settingsRepository.updateWordsPerSession(count)
        }
    }
    
    fun updateShowStatistics(show: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateShowStatistics(show)
        }
    }
    
    fun updateFontSize(size: Int) {
        viewModelScope.launch {
            settingsRepository.updateFontSize(size)
        }
    }
    
    fun updateBackgroundColor(color: Long) {
        viewModelScope.launch {
            settingsRepository.updateBackgroundColor(color)
        }
    }
    
    fun updateWordColor(color: Long) {
        viewModelScope.launch {
            settingsRepository.updateWordColor(color)
        }
    }
    
    fun updateDiacriticsColor(color: Long) {
        viewModelScope.launch {
            settingsRepository.updateDiacriticsColor(color)
        }
    }
}
