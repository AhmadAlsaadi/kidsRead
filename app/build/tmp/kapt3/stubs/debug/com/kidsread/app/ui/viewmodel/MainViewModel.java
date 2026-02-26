package com.kidsread.app.ui.viewmodel;

/**
 * ViewModel للصفحة الرئيسية
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010*\u001a\u00020+J\u000e\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\rJ\b\u0010.\u001a\u00020+H\u0002J\b\u0010/\u001a\u00020+H\u0002J\u0006\u00100\u001a\u00020+R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00120\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00100\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u001d\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00120\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u000e\u0010&\u001a\u00020\'X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/kidsread/app/ui/viewmodel/MainViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_currentWord", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/kidsread/app/data/model/Word;", "_currentWordIndex", "", "_errorMessage", "", "_isLoading", "", "_sessionCompleted", "_sessionStats", "Lcom/kidsread/app/ui/viewmodel/SessionStats;", "_sessionWords", "", "appSettings", "Lcom/kidsread/app/data/model/AppSettings;", "getAppSettings", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "currentWord", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentWord", "()Lkotlinx/coroutines/flow/StateFlow;", "currentWordIndex", "getCurrentWordIndex", "errorMessage", "getErrorMessage", "isLoading", "sessionCompleted", "getSessionCompleted", "sessionStats", "getSessionStats", "sessionWords", "getSessionWords", "settingsRepository", "Lcom/kidsread/app/data/repository/SettingsRepository;", "wordRepository", "Lcom/kidsread/app/data/repository/WordRepository;", "clearError", "", "evaluateWord", "isMastered", "moveToNextWord", "reloadSessionWithCurrentSettings", "startNewSession", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.kidsread.app.data.repository.WordRepository wordRepository = null;
    @org.jetbrains.annotations.NotNull
    private final com.kidsread.app.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.kidsread.app.data.model.Word> _currentWord = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.kidsread.app.data.model.Word> currentWord = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.kidsread.app.data.model.Word>> _sessionWords = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.kidsread.app.data.model.Word>> sessionWords = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _currentWordIndex = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> currentWordIndex = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _sessionCompleted = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> sessionCompleted = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.kidsread.app.ui.viewmodel.SessionStats> _sessionStats = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.kidsread.app.ui.viewmodel.SessionStats> sessionStats = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.kidsread.app.data.model.AppSettings> appSettings = null;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.kidsread.app.data.model.Word> getCurrentWord() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.kidsread.app.data.model.Word>> getSessionWords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getCurrentWordIndex() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getSessionCompleted() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getErrorMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.kidsread.app.ui.viewmodel.SessionStats> getSessionStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.MutableStateFlow<com.kidsread.app.data.model.AppSettings> getAppSettings() {
        return null;
    }
    
    /**
     * إعادة تحميل الجلسة الحالية بناءً على الإعدادات الجديدة
     */
    private final void reloadSessionWithCurrentSettings() {
    }
    
    /**
     * بدء جلسة تعليم جديدة
     */
    public final void startNewSession() {
    }
    
    /**
     * تقييم قراءة الكلمة الحالية
     */
    public final void evaluateWord(boolean isMastered) {
    }
    
    /**
     * الانتقال للكلمة التالية
     */
    private final void moveToNextWord() {
    }
    
    /**
     * مسح رسالة الخطأ
     */
    public final void clearError() {
    }
}