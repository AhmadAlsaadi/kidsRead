package com.kidsread.app.data.model;

/**
 * إعدادات التطبيق
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\tH\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\fH\u00c6\u0003J\t\u0010\"\u001a\u00020\fH\u00c6\u0003J\t\u0010#\u001a\u00020\fH\u00c6\u0003J_\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\fH\u00c6\u0001J\u0013\u0010%\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00d6\u0001J\t\u0010(\u001a\u00020)H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014\u00a8\u0006*"}, d2 = {"Lcom/kidsread/app/data/model/AppSettings;", "", "wordLength", "", "selectedDiacritics", "", "Lcom/kidsread/app/data/model/DiacriticType;", "wordsPerSession", "showStatistics", "", "fontSize", "backgroundColor", "", "wordColor", "diacriticsColor", "(ILjava/util/Set;IZIJJJ)V", "getBackgroundColor", "()J", "getDiacriticsColor", "getFontSize", "()I", "getSelectedDiacritics", "()Ljava/util/Set;", "getShowStatistics", "()Z", "getWordColor", "getWordLength", "getWordsPerSession", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
public final class AppSettings {
    private final int wordLength = 0;
    @org.jetbrains.annotations.NotNull
    private final java.util.Set<com.kidsread.app.data.model.DiacriticType> selectedDiacritics = null;
    private final int wordsPerSession = 0;
    private final boolean showStatistics = false;
    private final int fontSize = 0;
    private final long backgroundColor = 0L;
    private final long wordColor = 0L;
    private final long diacriticsColor = 0L;
    
    public AppSettings(int wordLength, @org.jetbrains.annotations.NotNull
    java.util.Set<? extends com.kidsread.app.data.model.DiacriticType> selectedDiacritics, int wordsPerSession, boolean showStatistics, int fontSize, long backgroundColor, long wordColor, long diacriticsColor) {
        super();
    }
    
    public final int getWordLength() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Set<com.kidsread.app.data.model.DiacriticType> getSelectedDiacritics() {
        return null;
    }
    
    public final int getWordsPerSession() {
        return 0;
    }
    
    public final boolean getShowStatistics() {
        return false;
    }
    
    public final int getFontSize() {
        return 0;
    }
    
    public final long getBackgroundColor() {
        return 0L;
    }
    
    public final long getWordColor() {
        return 0L;
    }
    
    public final long getDiacriticsColor() {
        return 0L;
    }
    
    public AppSettings() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Set<com.kidsread.app.data.model.DiacriticType> component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final long component6() {
        return 0L;
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.kidsread.app.data.model.AppSettings copy(int wordLength, @org.jetbrains.annotations.NotNull
    java.util.Set<? extends com.kidsread.app.data.model.DiacriticType> selectedDiacritics, int wordsPerSession, boolean showStatistics, int fontSize, long backgroundColor, long wordColor, long diacriticsColor) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}