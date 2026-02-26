package com.kidsread.app.data.repository

import com.kidsread.app.data.database.WordDao
import com.kidsread.app.data.model.DiacriticType
import com.kidsread.app.data.model.Word
import kotlinx.coroutines.flow.Flow

/**
 * مستودع الكلمات
 */
class WordRepository(private val wordDao: WordDao) {
    
    fun getAllWords(): Flow<List<Word>> = wordDao.getAllWords()
    
    fun getMasteredWords(): Flow<List<Word>> = wordDao.getMasteredWords()
    
    suspend fun getWordById(id: Long): Word? = wordDao.getWordById(id)
    
    suspend fun insertWord(word: Word): Long = wordDao.insertWord(word)
    
    suspend fun insertWords(words: List<Word>) = wordDao.insertWords(words)
    
    suspend fun updateWord(word: Word) = wordDao.updateWord(word)
    
    suspend fun getWordsForReview(
        length: Int,
        diacriticTypes: Set<DiacriticType>,
        limit: Int
    ): List<Word> {
        val currentTime = System.currentTimeMillis()
        val typeNames = diacriticTypes.map { it.name }
        
        // محاولة الحصول على كلمات تحتاج مراجعة
        var words = wordDao.getWordsForReview(length, typeNames, currentTime, limit)
        
        // إذا لم يكن هناك كلمات كافية، نضيف كلمات عشوائية
        if (words.size < limit) {
            val randomWords = wordDao.getRandomWords(
                length, 
                typeNames, 
                limit - words.size
            )
            words = words + randomWords
        }
        
        return words.take(limit)
    }
    
    suspend fun getWordCount(length: Int, diacriticTypes: Set<DiacriticType>): Int {
        val typeNames = diacriticTypes.map { it.name }
        return wordDao.getWordCount(length, typeNames)
    }
    
    suspend fun getTotalWordCount(): Int = wordDao.getTotalWordCount()
    
    suspend fun deleteAllWords() = wordDao.deleteAllWords()
}
