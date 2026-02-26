package com.kidsread.app.data.model

/**
 * أنواع التشكيل المتاحة
 */
enum class DiacriticType(val arabicName: String) {
    FATHA("الفتحة"),
    KASRA("الكسرة"),
    DAMMA("الضمة"),
    SUKOON("السكون"),
    TANWEEN("التنوين"),
    MIXED("مختلط"),
    ALL("الكل")
}
