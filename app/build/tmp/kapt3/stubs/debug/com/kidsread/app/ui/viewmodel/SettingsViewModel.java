package com.kidsread.app.ui.viewmodel;

/**
 * ViewModel لصفحة الإعدادات
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u0019J\u000e\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u0019R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/kidsread/app/ui/viewmodel/SettingsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_settings", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/kidsread/app/data/model/AppSettings;", "settings", "Lkotlinx/coroutines/flow/StateFlow;", "getSettings", "()Lkotlinx/coroutines/flow/StateFlow;", "settingsRepository", "Lcom/kidsread/app/data/repository/SettingsRepository;", "loadSettings", "", "toggleDiacriticType", "type", "Lcom/kidsread/app/data/model/DiacriticType;", "updateBackgroundColor", "color", "", "updateDiacriticsColor", "updateFontSize", "size", "", "updateShowStatistics", "show", "", "updateWordColor", "updateWordLength", "length", "updateWordsPerSession", "count", "app_debug"})
public final class SettingsViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.kidsread.app.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.kidsread.app.data.model.AppSettings> _settings = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.kidsread.app.data.model.AppSettings> settings = null;
    
    public SettingsViewModel(@org.jetbrains.annotations.NotNull
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.kidsread.app.data.model.AppSettings> getSettings() {
        return null;
    }
    
    private final void loadSettings() {
    }
    
    public final void updateWordLength(int length) {
    }
    
    public final void toggleDiacriticType(@org.jetbrains.annotations.NotNull
    com.kidsread.app.data.model.DiacriticType type) {
    }
    
    public final void updateWordsPerSession(int count) {
    }
    
    public final void updateShowStatistics(boolean show) {
    }
    
    public final void updateFontSize(int size) {
    }
    
    public final void updateBackgroundColor(long color) {
    }
    
    public final void updateWordColor(long color) {
    }
    
    public final void updateDiacriticsColor(long color) {
    }
}