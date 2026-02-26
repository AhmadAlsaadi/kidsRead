package com.kidsread.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * نموذج بيانات الكلمة في قاعدة البيانات
 */
@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    // الكلمة مع التشكيل
    val word: String,
    
    // طول الكلمة (عدد الأحرف)
    val length: Int,
    
    // نوع التشكيل: fatha, kasra, damma, sukoon, tanween, mixed
    val diacriticType: String,
    
    // مستوى الصعوبة
    val difficulty: Int = 1,
    
    // عدد المراجعات
    val reviewCount: Int = 0,
    
    // عدد المرات التي تم الإتقان فيها
    val masteredCount: Int = 0,
    
    // عدد المرات التي لم يتم الإتقان فيها
    val failedCount: Int = 0,
    
    // آخر مرة تمت مراجعة الكلمة (timestamp)
    val lastReviewDate: Long = 0,
    
    // التاريخ التالي للمراجعة (timestamp)
    val nextReviewDate: Long = System.currentTimeMillis(),
    
    // الفاصل الزمني للمراجعة بالأيام
    val interval: Int = 1,
    
    // معامل السهولة (يستخدم في خوارزمية التذكر المتباعد)
    val easeFactor: Float = 2.5f,
    
    // هل تم إتقان الكلمة؟
    val isMastered: Boolean = false
)
