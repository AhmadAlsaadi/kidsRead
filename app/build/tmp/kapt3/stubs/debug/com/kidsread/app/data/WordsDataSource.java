package com.kidsread.app.data;

/**
 * بيانات الكلمات الأولية - مجموعة متنوعة من الكلمات العربية بالتشكيل
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/kidsread/app/data/WordsDataSource;", "", "()V", "getAllWords", "", "Lcom/kidsread/app/data/model/Word;", "getThreeLetterBoostWords", "populateDatabase", "", "wordDao", "Lcom/kidsread/app/data/database/WordDao;", "(Lcom/kidsread/app/data/database/WordDao;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class WordsDataSource {
    @org.jetbrains.annotations.NotNull
    public static final com.kidsread.app.data.WordsDataSource INSTANCE = null;
    
    private WordsDataSource() {
        super();
    }
    
    /**
     * ملء قاعدة البيانات بالكلمات الأولية
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object populateDatabase(@org.jetbrains.annotations.NotNull
    com.kidsread.app.data.database.WordDao wordDao, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.util.List<com.kidsread.app.data.model.Word> getAllWords() {
        return null;
    }
    
    private final java.util.List<com.kidsread.app.data.model.Word> getThreeLetterBoostWords() {
        return null;
    }
}