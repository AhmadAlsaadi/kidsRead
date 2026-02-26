package com.kidsread.app.data.repository;

/**
 * مستودع الكلمات
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tJ\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tJ\u000e\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J$\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0018J2\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001a\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u001c\u0010\u001f\u001a\u00020\u00062\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/kidsread/app/data/repository/WordRepository;", "", "wordDao", "Lcom/kidsread/app/data/database/WordDao;", "(Lcom/kidsread/app/data/database/WordDao;)V", "deleteAllWords", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWords", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/kidsread/app/data/model/Word;", "getMasteredWords", "getTotalWordCount", "", "getWordById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordCount", "length", "diacriticTypes", "", "Lcom/kidsread/app/data/model/DiacriticType;", "(ILjava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordsForReview", "limit", "(ILjava/util/Set;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWord", "word", "(Lcom/kidsread/app/data/model/Word;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWords", "words", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateWord", "app_debug"})
public final class WordRepository {
    @org.jetbrains.annotations.NotNull
    private final com.kidsread.app.data.database.WordDao wordDao = null;
    
    public WordRepository(@org.jetbrains.annotations.NotNull
    com.kidsread.app.data.database.WordDao wordDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kidsread.app.data.model.Word>> getAllWords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.kidsread.app.data.model.Word>> getMasteredWords() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getWordById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kidsread.app.data.model.Word> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertWord(@org.jetbrains.annotations.NotNull
    com.kidsread.app.data.model.Word word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertWords(@org.jetbrains.annotations.NotNull
    java.util.List<com.kidsread.app.data.model.Word> words, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateWord(@org.jetbrains.annotations.NotNull
    com.kidsread.app.data.model.Word word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getWordsForReview(int length, @org.jetbrains.annotations.NotNull
    java.util.Set<? extends com.kidsread.app.data.model.DiacriticType> diacriticTypes, int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.kidsread.app.data.model.Word>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getWordCount(int length, @org.jetbrains.annotations.NotNull
    java.util.Set<? extends com.kidsread.app.data.model.DiacriticType> diacriticTypes, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTotalWordCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteAllWords(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}