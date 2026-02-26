package com.kidsread.app.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kidsread.app.data.model.AppSettings
import com.kidsread.app.data.model.DiacriticType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * مستودع الإعدادات
 */
class SettingsRepository(private val context: Context) {
    
    companion object {
        private val WORD_LENGTH_KEY = intPreferencesKey("word_length")
        private val SELECTED_DIACRITICS_KEY = stringSetPreferencesKey("selected_diacritics")
        private val WORDS_PER_SESSION_KEY = intPreferencesKey("words_per_session")
        private val SHOW_STATISTICS_KEY = booleanPreferencesKey("show_statistics")
        private val FONT_SIZE_KEY = intPreferencesKey("font_size")
        private val BACKGROUND_COLOR_KEY = longPreferencesKey("background_color")
        private val WORD_COLOR_KEY = longPreferencesKey("word_color")
        private val DIACRITICS_COLOR_KEY = longPreferencesKey("diacritics_color")
    }
    
    val settingsFlow: Flow<AppSettings> = context.dataStore.data.map { preferences ->
        val wordLength = preferences[WORD_LENGTH_KEY] ?: 1
        val diacriticsSet = preferences[SELECTED_DIACRITICS_KEY] ?: setOf(DiacriticType.FATHA.name)
        val wordsPerSession = preferences[WORDS_PER_SESSION_KEY] ?: 10
        val showStatistics = preferences[SHOW_STATISTICS_KEY] ?: true
        val fontSize = preferences[FONT_SIZE_KEY] ?: 72
        val backgroundColor = preferences[BACKGROUND_COLOR_KEY] ?: 0xFFFFFFFF
        val wordColor = preferences[WORD_COLOR_KEY] ?: 0xFF000000
        val diacriticsColor = preferences[DIACRITICS_COLOR_KEY] ?: 0xFFFF0000
        
        val selectedDiacritics = diacriticsSet.mapNotNull { 
            try {
                DiacriticType.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }.toSet()
        
        AppSettings(
            wordLength = wordLength,
            selectedDiacritics = if (selectedDiacritics.isEmpty()) setOf(DiacriticType.FATHA) else selectedDiacritics,
            wordsPerSession = wordsPerSession,
            showStatistics = showStatistics,
            fontSize = fontSize,
            backgroundColor = backgroundColor,
            wordColor = wordColor,
            diacriticsColor = diacriticsColor
        )
    }
    
    suspend fun updateWordLength(length: Int) {
        context.dataStore.edit { preferences ->
            preferences[WORD_LENGTH_KEY] = length.coerceIn(1, 5)
        }
    }
    
    suspend fun updateSelectedDiacritics(diacritics: Set<DiacriticType>) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_DIACRITICS_KEY] = diacritics.map { it.name }.toSet()
        }
    }
    
    suspend fun updateWordsPerSession(count: Int) {
        context.dataStore.edit { preferences ->
            preferences[WORDS_PER_SESSION_KEY] = count.coerceIn(5, 50)
        }
    }
    
    
    suspend fun updateShowStatistics(show: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SHOW_STATISTICS_KEY] = show
        }
    }
    
    suspend fun updateFontSize(size: Int) {
        context.dataStore.edit { preferences ->
            preferences[FONT_SIZE_KEY] = size.coerceIn(36, 120)
        }
    }
    
    suspend fun updateBackgroundColor(color: Long) {
        context.dataStore.edit { preferences ->
            preferences[BACKGROUND_COLOR_KEY] = color
        }
    }
    
    suspend fun updateWordColor(color: Long) {
        context.dataStore.edit { preferences ->
            preferences[WORD_COLOR_KEY] = color
        }
    }
    
    suspend fun updateDiacriticsColor(color: Long) {
        context.dataStore.edit { preferences ->
            preferences[DIACRITICS_COLOR_KEY] = color
        }
    }
}
