package com.kidsread.app.algorithm

import com.kidsread.app.data.model.Word
import kotlin.math.max

/**
 * خوارزمية التذكر المتباعد (Spaced Repetition System - SRS)
 * مبنية على خوارزمية SM-2 (SuperMemo 2)
 */
object SpacedRepetitionAlgorithm {
    
    // الحد الأدنى لمعامل السهولة
    private const val MIN_EASE_FACTOR = 1.3f
    
    // الحد الأقصى لمعامل السهولة
    private const val MAX_EASE_FACTOR = 2.5f
    
    // عدد الـ milliseconds في اليوم الواحد
    private const val MILLIS_PER_DAY = 24L * 60L * 60L * 1000L
    
    /**
     * تحديث الكلمة بناءً على نتيجة المراجعة
     * @param word الكلمة
     * @param isMastered هل تمت قراءة الكلمة بشكل صحيح؟
     * @return الكلمة المحدثة
     */
    fun updateWord(word: Word, isMastered: Boolean): Word {
        val currentTime = System.currentTimeMillis()
        
        return if (isMastered) {
            // إذا تم إتقان الكلمة
            val newInterval = calculateNextInterval(word.interval, word.easeFactor)
            val newEaseFactor = calculateEaseFactor(word.easeFactor, 5) // درجة 5 للإتقان
            
            word.copy(
                reviewCount = word.reviewCount + 1,
                masteredCount = word.masteredCount + 1,
                lastReviewDate = currentTime,
                nextReviewDate = currentTime + (newInterval.toLong() * MILLIS_PER_DAY),
                interval = newInterval,
                easeFactor = newEaseFactor,
                isMastered = word.masteredCount + 1 >= 3 // يعتبر متقناً بعد 3 مرات نجاح
            )
        } else {
            // إذا لم يتم إتقان الكلمة
            val newEaseFactor = calculateEaseFactor(word.easeFactor, 2) // درجة 2 للفشل
            
            word.copy(
                reviewCount = word.reviewCount + 1,
                failedCount = word.failedCount + 1,
                lastReviewDate = currentTime,
                nextReviewDate = currentTime + MILLIS_PER_DAY, // إعادة المراجعة بعد يوم واحد
                interval = 1, // إعادة تعيين الفاصل إلى يوم واحد
                easeFactor = newEaseFactor,
                isMastered = false // إلغاء حالة الإتقان
            )
        }
    }
    
    /**
     * حساب الفاصل الزمني التالي
     */
    private fun calculateNextInterval(currentInterval: Int, easeFactor: Float): Int {
        return when (currentInterval) {
            0 -> 1
            1 -> 6
            else -> (currentInterval * easeFactor).toInt()
        }.coerceAtMost(365) // الحد الأقصى سنة واحدة
    }
    
    /**
     * حساب معامل السهولة الجديد
     * @param currentEaseFactor معامل السهولة الحالي
     * @param quality الجودة (0-5): 0 = فشل كامل، 5 = إتقان كامل
     */
    private fun calculateEaseFactor(currentEaseFactor: Float, quality: Int): Float {
        val q = quality.coerceIn(0, 5)
        val newEaseFactor = currentEaseFactor + (0.1f - (5 - q) * (0.08f + (5 - q) * 0.02f))
        return max(newEaseFactor, MIN_EASE_FACTOR).coerceAtMost(MAX_EASE_FACTOR)
    }
    
    /**
     * حساب نسبة النجاح
     */
    fun calculateSuccessRate(word: Word): Float {
        if (word.reviewCount == 0) return 0f
        return (word.masteredCount.toFloat() / word.reviewCount.toFloat()) * 100f
    }
    
    /**
     * حساب مستوى الصعوبة بناءً على الأداء
     */
    fun calculateDifficulty(word: Word): Int {
        val successRate = calculateSuccessRate(word)
        return when {
            successRate >= 80 -> 1 // سهل
            successRate >= 50 -> 2 // متوسط
            else -> 3 // صعب
        }
    }
}
