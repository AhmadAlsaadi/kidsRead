package com.kidsread.app.utils;

/**
 * دوال مساعدة للتطبيق
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0013"}, d2 = {"Lcom/kidsread/app/utils/AppUtils;", "", "()V", "formatDate", "", "timestamp", "", "formatTime", "getDaysDifference", "", "timestamp1", "timestamp2", "getSuccessRateColor", "rate", "", "getSuccessRateDescription", "isFuture", "", "isPast", "app_debug"})
public final class AppUtils {
    @org.jetbrains.annotations.NotNull
    public static final com.kidsread.app.utils.AppUtils INSTANCE = null;
    
    private AppUtils() {
        super();
    }
    
    /**
     * تنسيق التاريخ
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String formatDate(long timestamp) {
        return null;
    }
    
    /**
     * تنسيق الوقت
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String formatTime(long timestamp) {
        return null;
    }
    
    /**
     * حساب الفرق الزمني بالأيام
     */
    public final int getDaysDifference(long timestamp1, long timestamp2) {
        return 0;
    }
    
    /**
     * التحقق من كون التاريخ في الماضي
     */
    public final boolean isPast(long timestamp) {
        return false;
    }
    
    /**
     * التحقق من كون التاريخ في المستقبل
     */
    public final boolean isFuture(long timestamp) {
        return false;
    }
    
    /**
     * الحصول على وصف النسبة المئوية
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSuccessRateDescription(float rate) {
        return null;
    }
    
    /**
     * الحصول على لون حسب النسبة المئوية
     */
    public final int getSuccessRateColor(float rate) {
        return 0;
    }
}