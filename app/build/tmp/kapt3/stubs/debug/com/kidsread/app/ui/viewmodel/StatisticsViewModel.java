package com.kidsread.app.ui.viewmodel;

/**
 * ViewModel للإحصائيات والتقارير
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006\u0017"}, d2 = {"Lcom/kidsread/app/ui/viewmodel/StatisticsViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_sessionHistory", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/kidsread/app/ui/viewmodel/SessionRecord;", "_wordStatistics", "", "", "Lcom/kidsread/app/ui/viewmodel/WordStatistic;", "sessionHistory", "Lkotlinx/coroutines/flow/StateFlow;", "getSessionHistory", "()Lkotlinx/coroutines/flow/StateFlow;", "wordStatistics", "getWordStatistics", "addSessionRecord", "", "record", "updateWordStatistic", "word", "Lcom/kidsread/app/data/model/Word;", "app_debug"})
public final class StatisticsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Map<java.lang.Long, com.kidsread.app.ui.viewmodel.WordStatistic>> _wordStatistics = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.Long, com.kidsread.app.ui.viewmodel.WordStatistic>> wordStatistics = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.kidsread.app.ui.viewmodel.SessionRecord>> _sessionHistory = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.kidsread.app.ui.viewmodel.SessionRecord>> sessionHistory = null;
    
    public StatisticsViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.Long, com.kidsread.app.ui.viewmodel.WordStatistic>> getWordStatistics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.kidsread.app.ui.viewmodel.SessionRecord>> getSessionHistory() {
        return null;
    }
    
    public final void addSessionRecord(@org.jetbrains.annotations.NotNull
    com.kidsread.app.ui.viewmodel.SessionRecord record) {
    }
    
    public final void updateWordStatistic(@org.jetbrains.annotations.NotNull
    com.kidsread.app.data.model.Word word) {
    }
}