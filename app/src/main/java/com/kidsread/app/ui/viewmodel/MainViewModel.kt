package com.kidsread.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kidsread.app.R
import com.kidsread.app.algorithm.SpacedRepetitionAlgorithm
import com.kidsread.app.data.database.AppDatabase
import com.kidsread.app.data.model.Word
import com.kidsread.app.data.repository.SettingsRepository
import com.kidsread.app.data.repository.WordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel للصفحة الرئيسية
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    
    private val wordRepository: WordRepository
    private val settingsRepository: SettingsRepository
    
    private val _currentWord = MutableStateFlow<Word?>(null)
    val currentWord: StateFlow<Word?> = _currentWord.asStateFlow()
    
    private val _sessionWords = MutableStateFlow<List<Word>>(emptyList())
    val sessionWords: StateFlow<List<Word>> = _sessionWords.asStateFlow()
    
    private val _currentWordIndex = MutableStateFlow(0)
    val currentWordIndex: StateFlow<Int> = _currentWordIndex.asStateFlow()
    
    private val _sessionCompleted = MutableStateFlow(false)
    val sessionCompleted: StateFlow<Boolean> = _sessionCompleted.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    // إحصائيات الجلسة
    private val _sessionStats = MutableStateFlow(SessionStats())
    val sessionStats: StateFlow<SessionStats> = _sessionStats.asStateFlow()
    
    // الإعدادات
    val appSettings = MutableStateFlow(com.kidsread.app.data.model.AppSettings())
    
    init {
        val database = AppDatabase.getDatabase(application)
        wordRepository = WordRepository(database.wordDao())
        settingsRepository = SettingsRepository(application)
        
        // مراقبة تغييرات الإعدادات
        viewModelScope.launch {
            settingsRepository.settingsFlow.collect { settings ->
                appSettings.value = settings
                
                // إذا تغير عدد الأحرف أو أنواع التشكيل، أعد تحميل الجلسة الحالية
                if (_sessionWords.value.isNotEmpty()) {
                    reloadSessionWithCurrentSettings()
                }
            }
        }
    }
    
    /**
     * إعادة تحميل الجلسة الحالية بناءً على الإعدادات الجديدة
     */
    private fun reloadSessionWithCurrentSettings() {
        viewModelScope.launch {
            try {
                val settings = appSettings.value
                val currentIndex = _currentWordIndex.value
                
                // الحصول على كلمات جديدة بناءً على الإعدادات المحدثة
                val words = wordRepository.getWordsForReview(
                    settings.wordLength,
                    settings.selectedDiacritics,
                    settings.wordsPerSession
                )
                
                _sessionWords.value = words
                
                // تحديث الكلمة الحالية
                if (words.isNotEmpty()) {
                    val newIndex = if (currentIndex < words.size) currentIndex else 0
                    _currentWordIndex.value = newIndex
                    _currentWord.value = words[newIndex]
                } else {
                    _errorMessage.value = getApplication<Application>().getString(R.string.error_no_words_for_review)
                    _currentWord.value = null
                }
            } catch (e: Exception) {
                _errorMessage.value = getApplication<Application>().getString(R.string.error_general, e.message ?: "")
            }
        }
    }
    
    /**
     * بدء جلسة تعليم جديدة
     */
    fun startNewSession() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _sessionCompleted.value = false
                _currentWordIndex.value = 0
                _sessionStats.value = SessionStats()
                
                val settings = settingsRepository.settingsFlow.first()
                
                // التحقق من وجود كلمات
                val wordCount = wordRepository.getWordCount(
                    settings.wordLength,
                    settings.selectedDiacritics
                )
                
                if (wordCount == 0) {
                    _errorMessage.value = getApplication<Application>().getString(R.string.error_no_words)
                    _isLoading.value = false
                    return@launch
                }
                
                // الحصول على كلمات للمراجعة
                val words = wordRepository.getWordsForReview(
                    settings.wordLength,
                    settings.selectedDiacritics,
                    settings.wordsPerSession
                )
                
                _sessionWords.value = words
                
                if (words.isNotEmpty()) {
                    _currentWord.value = words[0]
                } else {
                    _errorMessage.value = getApplication<Application>().getString(R.string.error_no_words_for_review)
                }
                
            } catch (e: Exception) {
                _errorMessage.value = getApplication<Application>().getString(R.string.error_general, e.message ?: "")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * تقييم قراءة الكلمة الحالية
     */
    fun evaluateWord(isMastered: Boolean) {
        viewModelScope.launch {
            val word = _currentWord.value ?: return@launch
            
            // تحديث الكلمة باستخدام خوارزمية التذكر المتباعد
            val updatedWord = SpacedRepetitionAlgorithm.updateWord(word, isMastered)
            wordRepository.updateWord(updatedWord)
            
            // تحديث الإحصائيات
            val currentStats = _sessionStats.value
            _sessionStats.value = if (isMastered) {
                currentStats.copy(
                    masteredCount = currentStats.masteredCount + 1,
                    totalReviewed = currentStats.totalReviewed + 1
                )
            } else {
                currentStats.copy(
                    failedCount = currentStats.failedCount + 1,
                    totalReviewed = currentStats.totalReviewed + 1
                )
            }
            
            // الانتقال للكلمة التالية
            moveToNextWord()
        }
    }
    
    /**
     * الانتقال للكلمة التالية
     */
    private fun moveToNextWord() {
        val words = _sessionWords.value
        val nextIndex = _currentWordIndex.value + 1
        
        if (nextIndex < words.size) {
            _currentWordIndex.value = nextIndex
            _currentWord.value = words[nextIndex]
        } else {
            // انتهت الجلسة
            _sessionCompleted.value = true
            _currentWord.value = null
        }
    }
    
    /**
     * مسح رسالة الخطأ
     */
    fun clearError() {
        _errorMessage.value = null
    }
}

/**
 * إحصائيات الجلسة
 */
data class SessionStats(
    val totalReviewed: Int = 0,
    val masteredCount: Int = 0,
    val failedCount: Int = 0
) {
    val successRate: Float
        get() = if (totalReviewed > 0) {
            (masteredCount.toFloat() / totalReviewed.toFloat()) * 100f
        } else {
            0f
        }
}
