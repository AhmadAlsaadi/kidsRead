package com.kidsread.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kidsread.app.data.model.Word
import kotlinx.coroutines.flow.Flow

/**
 * واجهة الوصول لقاعدة بيانات الكلمات
 */
@Dao
interface WordDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<Word>)
    
    @Update
    suspend fun updateWord(word: Word)
    
    @Query("SELECT * FROM words WHERE id = :wordId")
    suspend fun getWordById(wordId: Long): Word?
    
    @Query("SELECT * FROM words WHERE length = :length AND diacriticType IN (:diacriticTypes) AND nextReviewDate <= :currentTime ORDER BY nextReviewDate ASC LIMIT :limit")
    suspend fun getWordsForReview(
        length: Int,
        diacriticTypes: List<String>,
        currentTime: Long,
        limit: Int
    ): List<Word>
    
    @Query("SELECT * FROM words WHERE length = :length AND diacriticType IN (:diacriticTypes) ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomWords(
        length: Int,
        diacriticTypes: List<String>,
        limit: Int
    ): List<Word>
    
    @Query("SELECT * FROM words ORDER BY word ASC")
    fun getAllWords(): Flow<List<Word>>
    
    @Query("SELECT COUNT(*) FROM words WHERE length = :length AND diacriticType IN (:diacriticTypes)")
    suspend fun getWordCount(length: Int, diacriticTypes: List<String>): Int
    
    @Query("SELECT * FROM words WHERE isMastered = 1")
    fun getMasteredWords(): Flow<List<Word>>
    
    @Query("DELETE FROM words")
    suspend fun deleteAllWords()
    
    @Query("SELECT COUNT(*) FROM words")
    suspend fun getTotalWordCount(): Int
}
