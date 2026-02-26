package com.kidsread.app.utils;

/**
 * أدوات مساعدة للتاريخ والوقت
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0004\u00a8\u0006\r"}, d2 = {"Lcom/kidsread/app/utils/DateTimeUtils;", "", "()V", "daysBetween", "", "timestamp1", "", "timestamp2", "formatTime", "", "timestamp", "getNextReviewDate", "intervalDays", "app_debug"})
public final class DateTimeUtils {
    @org.jetbrains.annotations.NotNull
    public static final com.kidsread.app.utils.DateTimeUtils INSTANCE = null;
    
    private DateTimeUtils() {
        super();
    }
    
    /**
     * تحويل timestamp إلى نص مقروء
     */
    @org.jetbrains.annotations.NotNull
    public final java.lang.String formatTime(long timestamp) {
        return null;
    }
    
    /**
     * تحويل الفاصل الزمني (بالأيام) إلى تاريخ مستقبلي
     */
    public final long getNextReviewDate(int intervalDays) {
        return 0L;
    }
    
    /**
     * حساب الفرق بالأيام بين تاريخين
     */
    public final int daysBetween(long timestamp1, long timestamp2) {
        return 0;
    }
}