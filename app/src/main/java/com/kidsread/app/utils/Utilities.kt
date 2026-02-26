package com.kidsread.app.utils

import android.content.Context
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * أدوات مساعدة للتاريخ والوقت
 */
object DateTimeUtils {
    
    /**
     * تحويل timestamp إلى نص مقروء
     */
    fun formatTime(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp
        
        return when {
            diff < 60 * 1000 -> "للتو"
            diff < 60 * 60 * 1000 -> {
                val minutes = diff / (60 * 1000)
                "قبل $minutes دقيقة"
            }
            diff < 24 * 60 * 60 * 1000 -> {
                val hours = diff / (60 * 60 * 1000)
                "قبل $hours ساعة"
            }
            diff < 7 * 24 * 60 * 60 * 1000 -> {
                val days = diff / (24 * 60 * 60 * 1000)
                "قبل $days أيام"
            }
            else -> {
                val date = Date(timestamp)
                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale("ar"))
                formatter.format(date)
            }
        }
    }
    
    /**
     * تحويل الفاصل الزمني (بالأيام) إلى تاريخ مستقبلي
     */
    fun getNextReviewDate(intervalDays: Int): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, intervalDays)
        return calendar.timeInMillis
    }
    
    /**
     * حساب الفرق بالأيام بين تاريخين
     */
    fun daysBetween(timestamp1: Long, timestamp2: Long): Int {
        val diff = Math.abs(timestamp2 - timestamp1)
        return (diff / (24 * 60 * 60 * 1000)).toInt()
    }
}

/**
 * أدوات مساعدة للنسب والإحصائيات
 */
object StatisticUtils {
    
    /**
     * تحويل نسبة النجاح إلى نص ملون
     */
    fun getSuccessRateColor(rate: Float): Int {
        return when {
            rate >= 80 -> 0xFF4CAF50.toInt() // أخضر - ممتاز
            rate >= 60 -> 0xFF2196F3.toInt() // أزرق - جيد
            rate >= 40 -> 0xFFFFC107.toInt() // أصفر - متوسط
            else -> 0xFFF44336.toInt() // أحمر - ضعيف
        }
    }
    
    /**
     * تقييم الأداء
     */
    fun getRatingText(rate: Float): String {
        return when {
            rate >= 90 -> "ممتاز جداً! 🌟"
            rate >= 80 -> "ممتاز! ⭐"
            rate >= 70 -> "جيد جداً 👍"
            rate >= 60 -> "جيد 😊"
            rate >= 50 -> "يحتاج تحسن 📚"
            else -> "يحتاج مساعدة 💪"
        }
    }
    
    /**
     * حساب درجة الصعوبة النسبية
     */
    fun getDifficultyLevel(difficulty: Int): String {
        return when (difficulty) {
            1 -> "سهل 😄"
            2 -> "متوسط 😊"
            3 -> "صعب 😤"
            else -> "جداً صعب 🤔"
        }
    }
}
