package com.kidsread.app.data.model

/**
 * إعدادات التطبيق
 */
data class AppSettings(
    // طول الكلمة المطلوبة (1-5)
    val wordLength: Int = 1,
    
    // أنواع التشكيل المختارة
    val selectedDiacritics: Set<DiacriticType> = setOf(DiacriticType.FATHA),
    
    // عدد الكلمات في كل جلسة
    val wordsPerSession: Int = 10,
    
    // هل يتم عرض الإحصائيات؟
    val showStatistics: Boolean = true,
    
    // حجم الخط للكلمات
    val fontSize: Int = 72,
    
    // لون خلفية عرض الكلمة (ARGB)
    val backgroundColor: Long = 0xFFFFFFFF,
    
    // لون الكلمة (ARGB)
    val wordColor: Long = 0xFF000000,
    
    // لون الحركات (ARGB)
    val diacriticsColor: Long = 0xFFFF0000
)
