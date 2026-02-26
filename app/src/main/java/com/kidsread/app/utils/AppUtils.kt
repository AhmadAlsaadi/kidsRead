package com.kidsread.app.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * دوال مساعدة للتطبيق
 */
object AppUtils {
    
    /**
     * تنسيق التاريخ
     */
    fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        return sdf.format(Date(timestamp))
    }
    
    /**
     * تنسيق الوقت
     */
    fun formatTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        return sdf.format(Date(timestamp))
    }
    
    /**
     * حساب الفرق الزمني بالأيام
     */
    fun getDaysDifference(timestamp1: Long, timestamp2: Long): Int {
        val diffInMillis = Math.abs(timestamp2 - timestamp1)
        return (diffInMillis / (1000 * 60 * 60 * 24)).toInt()
    }
    
    /**
     * التحقق من كون التاريخ في الماضي
     */
    fun isPast(timestamp: Long): Boolean {
        return timestamp < System.currentTimeMillis()
    }
    
    /**
     * التحقق من كون التاريخ في المستقبل
     */
    fun isFuture(timestamp: Long): Boolean {
        return timestamp > System.currentTimeMillis()
    }
    
    /**
     * الحصول على وصف النسبة المئوية
     */
    fun getSuccessRateDescription(rate: Float): String {
        return when {
            rate >= 90 -> "ممتاز جداً"
            rate >= 80 -> "ممتاز"
            rate >= 70 -> "جيد جداً"
            rate >= 50 -> "جيد"
            else -> "يحتاج تحسين"
        }
    }
    
    /**
     * الحصول على لون حسب النسبة المئوية
     */
    fun getSuccessRateColor(rate: Float): Int {
        return when {
            rate >= 90 -> android.graphics.Color.parseColor("#4CAF50") // أخضر
            rate >= 80 -> android.graphics.Color.parseColor("#8BC34A") // أخضر فاتح
            rate >= 70 -> android.graphics.Color.parseColor("#FFC107") // أصفر
            rate >= 50 -> android.graphics.Color.parseColor("#FF9800") // برتقالي
            else -> android.graphics.Color.parseColor("#F44336") // أحمر
        }
    }
}
