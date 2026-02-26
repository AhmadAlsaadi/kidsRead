package com.kidsread.app

import android.app.Application
import androidx.lifecycle.lifecycleScope
import com.kidsread.app.data.WordsDataSource
import com.kidsread.app.data.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * فئة التطبيق الرئيسية
 */
class KidsReadApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // ملء قاعدة البيانات بالكلمات الأولية
        CoroutineScope(Dispatchers.IO).launch {
            val database = AppDatabase.getDatabase(applicationContext)
            WordsDataSource.populateDatabase(database.wordDao())
        }
    }
}
