package com.kidsread.app.algorithm

import com.kidsread.app.data.model.Word
import com.kidsread.app.data.model.DiacriticType
import org.junit.Assert.*
import org.junit.Test

/**
 * اختبارات خوارزمية التذكر المتباعد
 */
class SpacedRepetitionAlgorithmTest {
    
    @Test
    fun testUpdateWordMastered() {
        // جهّز
        val word = Word(
            id = 1,
            word = "بَيْتٌ",
            length = 3,
            diacriticType = DiacriticType.MIXED.name,
            reviewCount = 0,
            masteredCount = 0,
            failedCount = 0
        )
        
        // تنفيذ
        val updatedWord = SpacedRepetitionAlgorithm.updateWord(word, true)
        
        // تحقق
        assertEquals(1, updatedWord.reviewCount)
        assertEquals(1, updatedWord.masteredCount)
        assertEquals(0, updatedWord.failedCount)
        assertTrue(updatedWord.nextReviewDate > word.nextReviewDate)
    }
    
    @Test
    fun testUpdateWordFailed() {
        // جهّز
        val word = Word(
            id = 1,
            word = "بَيْتٌ",
            length = 3,
            diacriticType = DiacriticType.MIXED.name,
            reviewCount = 1,
            masteredCount = 1,
            failedCount = 0
        )
        
        // تنفيذ
        val updatedWord = SpacedRepetitionAlgorithm.updateWord(word, false)
        
        // تحقق
        assertEquals(2, updatedWord.reviewCount)
        assertEquals(1, updatedWord.masteredCount)
        assertEquals(1, updatedWord.failedCount)
        assertEquals(1, updatedWord.interval)
        assertFalse(updatedWord.isMastered)
    }
    
    @Test
    fun testCalculateSuccessRate() {
        // جهّز
        val word = Word(
            id = 1,
            word = "بَيْتٌ",
            length = 3,
            diacriticType = DiacriticType.MIXED.name,
            reviewCount = 10,
            masteredCount = 8,
            failedCount = 2
        )
        
        // تنفيذ
        val rate = SpacedRepetitionAlgorithm.calculateSuccessRate(word)
        
        // تحقق
        assertEquals(80f, rate)
    }
    
    @Test
    fun testCalculateDifficulty() {
        // جهّز - سهل
        val easyWord = Word(
            id = 1,
            word = "بَيْتٌ",
            length = 3,
            diacriticType = DiacriticType.MIXED.name,
            reviewCount = 10,
            masteredCount = 9,
            failedCount = 1
        )
        
        // تحقق
        assertEquals(1, SpacedRepetitionAlgorithm.calculateDifficulty(easyWord))
        
        // جهّز - صعب
        val hardWord = Word(
            id = 2,
            word = "كِتَابٌ",
            length = 3,
            diacriticType = DiacriticType.MIXED.name,
            reviewCount = 10,
            masteredCount = 3,
            failedCount = 7
        )
        
        // تحقق
        assertEquals(3, SpacedRepetitionAlgorithm.calculateDifficulty(hardWord))
    }
    
    @Test
    fun testMasteryProgress() {
        // محاكاة تقدم الإتقان
        var word = Word(
            id = 1,
            word = "بَيْتٌ",
            length = 3,
            diacriticType = DiacriticType.MIXED.name
        )
        
        // المراجعة الأولى - متقن
        word = SpacedRepetitionAlgorithm.updateWord(word, true)
        assertFalse(word.isMastered)
        assertEquals(1, word.masteredCount)
        
        // المراجعة الثانية - متقن
        word = SpacedRepetitionAlgorithm.updateWord(word, true)
        assertFalse(word.isMastered)
        assertEquals(2, word.masteredCount)
        
        // المراجعة الثالثة - متقن
        word = SpacedRepetitionAlgorithm.updateWord(word, true)
        assertTrue(word.isMastered)
        assertEquals(3, word.masteredCount)
    }
}
