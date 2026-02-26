package com.kidsread.app.data.database;

/**
 * واجهة الوصول لقاعدة بيانات الكلمات
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000e\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\'J2\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00072\u0006\u0010\u000f\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u0017J:\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00072\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u001c\u0010\u001e\u001a\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00a7@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\""}, d2 = {"Lcom/kidsread/app/data/database/WordDao;", "", "deleteAllWords", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWords", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/kidsread/app/data/model/Word;", "getMasteredWords", "getRandomWords", "length", "", "diacriticTypes", "", "limit", "(ILjava/util/List;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalWordCount", "getWordById", "wordId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordCount", "(ILjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordsForReview", "currentTime", "(ILjava/util/List;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWord", "word", "(Lcom/kidsread/app/data/model/Word;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWords", "words", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateWord", "app_debug"})
@androidx.room.Dao
public abstract interface WordDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertWord(@org.jetbrains.annotations.NotNull
    com.kidsread.app.data.model.Word word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertWords(@org.jetbrains.annotations.NotNull
    java.util.List<com.kidsread.app.data.model.Word> words, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateWord(@org.jetbrains.annotations.NotNull
    com.kidsread.app.data.model.Word word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM words WHERE id = :wordId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWordById(long wordId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.kidsread.app.data.model.Word> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM words WHERE length = :length AND diacriticType IN (:diacriticTypes) AND nextReviewDate <= :currentTime ORDER BY nextReviewDate ASC LIMIT :limit")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWordsForReview(int length, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> diacriticTypes, long currentTime, int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.kidsread.app.data.model.Word>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM words WHERE length = :length AND diacriticType IN (:diacriticTypes) ORDER BY RANDOM() LIMIT :limit")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRandomWords(int length, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> diacriticTypes, int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.kidsread.app.data.model.Word>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM words ORDER BY word ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kidsread.app.data.model.Word>> getAllWords();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM words WHERE length = :length AND diacriticType IN (:diacriticTypes)")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWordCount(int length, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> diacriticTypes, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM words WHERE isMastered = 1")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.kidsread.app.data.model.Word>> getMasteredWords();
    
    @androidx.room.Query(value = "DELETE FROM words")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllWords(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM words")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalWordCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}