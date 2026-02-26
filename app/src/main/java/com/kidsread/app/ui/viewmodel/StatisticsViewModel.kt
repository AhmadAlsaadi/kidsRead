package com.kidsread.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.kidsread.app.data.model.Word
import com.kidsread.app.algorithm.SpacedRepetitionAlgorithm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel للإحصائيات والتقارير
 */
class StatisticsViewModel : ViewModel() {
    
    private val _wordStatistics = MutableStateFlow<Map<Long, WordStatistic>>(emptyMap())
    val wordStatistics: StateFlow<Map<Long, WordStatistic>> = _wordStatistics.asStateFlow()
    
    private val _sessionHistory = MutableStateFlow<List<SessionRecord>>(emptyList())
    val sessionHistory: StateFlow<List<SessionRecord>> = _sessionHistory.asStateFlow()
    
    fun addSessionRecord(record: SessionRecord) {
        val currentHistory = _sessionHistory.value.toMutableList()
        currentHistory.add(0, record) // إضافة في البداية
        _sessionHistory.value = currentHistory
    }
    
    fun updateWordStatistic(word: Word) {
        val currentStats = _wordStatistics.value.toMutableMap()
        val successRate = SpacedRepetitionAlgorithm.calculateSuccessRate(word)
        currentStats[word.id] = WordStatistic(
            wordId = word.id,
            word = word.word,
            reviewCount = word.reviewCount,
            masteredCount = word.masteredCount,
            failedCount = word.failedCount,
            successRate = successRate,
            difficulty = SpacedRepetitionAlgorithm.calculateDifficulty(word),
            isMastered = word.isMastered
        )
        _wordStatistics.value = currentStats
    }
}

/**
 * إحصائيات الكلمة الواحدة
 */
data class WordStatistic(
    val wordId: Long,
    val word: String,
    val reviewCount: Int,
    val masteredCount: Int,
    val failedCount: Int,
    val successRate: Float,
    val difficulty: Int,
    val isMastered: Boolean
)

/**
 * سجل الجلسة
 */
data class SessionRecord(
    val sessionId: Long = System.currentTimeMillis(),
    val totalWords: Int,
    val masteredCount: Int,
    val failedCount: Int,
    val successRate: Float,
    val duration: Long, // بالثواني
    val timestamp: Long = System.currentTimeMillis()
) {
    val date: String
        get() {
            val dateFormat = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale("ar"))
            return dateFormat.format(java.util.Date(timestamp))
        }
}
