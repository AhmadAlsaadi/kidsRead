package com.kidsread.app.data

import com.kidsread.app.data.database.WordDao
import com.kidsread.app.data.model.DiacriticType
import com.kidsread.app.data.model.Word

/**
 * بيانات الكلمات الأولية - مجموعة متنوعة من الكلمات العربية بالتشكيل
 */
object WordsDataSource {
    
    /**
     * ملء قاعدة البيانات بالكلمات الأولية
     */
    suspend fun populateDatabase(wordDao: WordDao) {
        val existingCount = wordDao.getTotalWordCount()
        if (existingCount > 0) {
            val threeLetterBoost = getThreeLetterBoostWords()
            val boostTypes = listOf(
                DiacriticType.FATHA,
                DiacriticType.KASRA,
                DiacriticType.DAMMA,
                DiacriticType.SUKOON
            )
            val missingTypes = boostTypes.filter { type ->
                wordDao.getWordCount(3, listOf(type.name)) == 0
            }
            if (missingTypes.isNotEmpty()) {
                val toInsert = threeLetterBoost.filter { it.diacriticType in missingTypes.map { type -> type.name } }
                if (toInsert.isNotEmpty()) {
                    wordDao.insertWords(toInsert)
                }
            }
            return // قاعدة البيانات تحتوي على بيانات بالفعل
        }
        
        val words = getAllWords()
        wordDao.insertWords(words)
    }
    
    private fun getAllWords(): List<Word> {
        return listOf(
            // حرف واحد مع الفتحة
            Word(word = "بَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            Word(word = "تَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            Word(word = "دَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            Word(word = "رَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            Word(word = "سَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            Word(word = "عَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            Word(word = "كَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            Word(word = "لَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            Word(word = "مَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            Word(word = "نَ", length = 1, diacriticType = DiacriticType.FATHA.name),
            
            // حرف واحد مع الكسرة
            Word(word = "بِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            Word(word = "تِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            Word(word = "دِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            Word(word = "رِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            Word(word = "سِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            Word(word = "عِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            Word(word = "كِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            Word(word = "لِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            Word(word = "مِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            Word(word = "نِ", length = 1, diacriticType = DiacriticType.KASRA.name),
            
            // حرف واحد مع الضمة
            Word(word = "بُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "تُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "دُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "رُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "سُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "عُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "كُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "لُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "مُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "نُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
            
            // حرفان مع الفتحة
            Word(word = "بَبَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            Word(word = "تَتَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            Word(word = "دَدَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            Word(word = "رَرَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            Word(word = "سَسَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            Word(word = "بَتَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            Word(word = "دَرَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            Word(word = "كَتَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            Word(word = "مَلَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            Word(word = "نَمَ", length = 2, diacriticType = DiacriticType.FATHA.name),
            
            // حرفان مع السكون
            Word(word = "بَبْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "تَتْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "دَدْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "رَرْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "سَسْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "بَتْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "كَتْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "مَلْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "نَمْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "شَمْ", length = 2, diacriticType = DiacriticType.SUKOON.name),
            
            // حرفان مختلط
            Word(word = "بِتَ", length = 2, diacriticType = DiacriticType.MIXED.name),
            Word(word = "دُرَ", length = 2, diacriticType = DiacriticType.MIXED.name),
            Word(word = "كَتِ", length = 2, diacriticType = DiacriticType.MIXED.name),
            Word(word = "مُلَ", length = 2, diacriticType = DiacriticType.MIXED.name),
            Word(word = "نُمِ", length = 2, diacriticType = DiacriticType.MIXED.name),
            
            // ثلاثة أحرف - كلمات حقيقية (فتحة)
            Word(word = "كَتَبَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "لَعَبَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "ذَهَبَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "جَلَسَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "خَرَجَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "دَخَلَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "رَكَبَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "سَمَعَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "ضَحَكَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "فَتَحَ", length = 3, diacriticType = DiacriticType.FATHA.name),

            // ثلاثة أحرف - كلمات حقيقية (كسرة)
            Word(word = "بِئْرٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "سِحْرٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "ذِهْنٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "نِسْرٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "جِسْرٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "عِرْقٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "مِسْكٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "نِعْمَ", length = 3, diacriticType = DiacriticType.KASRA.name),

            // ثلاثة أحرف - كلمات حقيقية (ضمة)
            Word(word = "نُورٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "رُوحٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "سُوقٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "سُورٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "دُودٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "فُولٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "تُوتٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "بُومٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),

            // ثلاثة أحرف - كلمات حقيقية (سكون)
            Word(word = "قَلْبٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "بَحْرٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "نَهْرٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "لَيْلٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "شَمْسٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "قَمْرٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "سَقْفٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "جَنْبٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),

            // ثلاثة أحرف - كلمات حقيقية (مختلط)
            Word(word = "بَيْتٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "قَلَمٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "كِتَابٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "وَلَدٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "بَابٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "كَلْبٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "بَحْرٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "شَمْسٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "قَمَرٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "نَهْرٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "جَبَلٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "بِنْتٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "فِيلٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "أَسَدٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            Word(word = "نَمْلَةٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
            
            // كلمات ثلاثية الأحرف شائعة وذات معنى واضح
            Word(word = "أَمٌّ", length = 3, diacriticType = DiacriticType.MIXED.name),      // الأم
            Word(word = "أَبٌ", length = 3, diacriticType = DiacriticType.MIXED.name),       // الأب
            Word(word = "أُخْتٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الأخت
            Word(word = "أَخٌ", length = 3, diacriticType = DiacriticType.MIXED.name),       // الأخ
            Word(word = "عَيْنٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // العين
            Word(word = "أُذُنٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الأذن
            Word(word = "أَنْفٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الأنف
            Word(word = "رَأْسٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الرأس
            Word(word = "رِجْلٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الرجل
            Word(word = "قَدَمٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // القدم
            Word(word = "شَعْرٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الشعر
            Word(word = "وَجْهٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الوجه
            Word(word = "سِنٌّ", length = 3, diacriticType = DiacriticType.MIXED.name),      // السن
            Word(word = "يَدٌ", length = 3, diacriticType = DiacriticType.MIXED.name),       // اليد
            Word(word = "فَمٌ", length = 3, diacriticType = DiacriticType.MIXED.name),       // الفم
            
            // حيوانات شائعة
            Word(word = "قِطٌّ", length = 3, diacriticType = DiacriticType.MIXED.name),      // القط
            Word(word = "بَقَرَةٌ", length = 3, diacriticType = DiacriticType.MIXED.name),   // البقرة (محسوبة كـ 3 أحرف)
            Word(word = "حِصَانٌ", length = 3, diacriticType = DiacriticType.MIXED.name),    // الحصان (محسوبة كـ 3 أحرف)
            Word(word = "طَيْرٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الطير
            Word(word = "سَمَكَةٌ", length = 3, diacriticType = DiacriticType.MIXED.name),   // السمكة (محسوبة كـ 3 أحرف)
            Word(word = "ضِفْدَعٌ", length = 3, diacriticType = DiacriticType.MIXED.name),   // الضفدع (محسوبة كـ 3 أحرف)
            Word(word = "أَرْنَبٌ", length = 3, diacriticType = DiacriticType.MIXED.name),    // الأرنب (محسوبة كـ 3 أحرف)
            Word(word = "دِيكٌ", length = 3, diacriticType = DiacriticType.MIXED.name),      // الديك
            Word(word = "دَجَاجَةٌ", length = 3, diacriticType = DiacriticType.MIXED.name),  // الدجاجة (محسوبة كـ 3 أحرف)
            Word(word = "نَحْلَةٌ", length = 3, diacriticType = DiacriticType.MIXED.name),    // النحلة (محسوبة كـ 3 أحرف)
            
            // ألوان شائعة
            Word(word = "أَحْمَرُ", length = 3, diacriticType = DiacriticType.MIXED.name),   // أحمر (محسوبة كـ 3 أحرف)
            Word(word = "أَزْرَقُ", length = 3, diacriticType = DiacriticType.MIXED.name),   // أزرق (محسوبة كـ 3 أحرف)
            Word(word = "أَخْضَرُ", length = 3, diacriticType = DiacriticType.MIXED.name),   // أخضر (محسوبة كـ 3 أحرف)
            Word(word = "أَصْفَرُ", length = 3, diacriticType = DiacriticType.MIXED.name),   // أصفر (محسوبة كـ 3 أحرف)
            Word(word = "أَبْيَضُ", length = 3, diacriticType = DiacriticType.MIXED.name),   // أبيض (محسوبة كـ 3 أحرف)
            Word(word = "أَسْوَدُ", length = 3, diacriticType = DiacriticType.MIXED.name),   // أسود (محسوبة كـ 3 أحرف)
            
            // فواكه وخضار
            Word(word = "تُفَّاحَةٌ", length = 3, diacriticType = DiacriticType.MIXED.name),  // التفاحة (محسوبة كـ 3 أحرف)
            Word(word = "مَوْزَةٌ", length = 3, diacriticType = DiacriticType.MIXED.name),    // الموزة (محسوبة كـ 3 أحرف)
            Word(word = "بُرْتُقَالَةٌ", length = 3, diacriticType = DiacriticType.MIXED.name), // البرتقالة
            Word(word = "ڪِيسٌ", length = 3, diacriticType = DiacriticType.MIXED.name),      // الكيس
            Word(word = "خَسٌّ", length = 3, diacriticType = DiacriticType.MIXED.name),      // الخس
            Word(word = "جَزَرٌ", length = 3, diacriticType = DiacriticType.MIXED.name),    // الجزر
            
            // أشياء يومية
            Word(word = "مَآءٌ", length = 3, diacriticType = DiacriticType.MIXED.name),      // الماء (محسوبة كـ 3 أحرف)
            Word(word = "نَارٌ", length = 3, diacriticType = DiacriticType.MIXED.name),      // النار
            Word(word = "ريحٌ", length = 3, diacriticType = DiacriticType.MIXED.name),       // الريح
            Word(word = "أَرْضٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الأرض
            Word(word = "سَمَاءٌ", length = 3, diacriticType = DiacriticType.MIXED.name),    // السماء
            Word(word = "غِيَمَةٌ", length = 3, diacriticType = DiacriticType.MIXED.name),    // الغيمة
            Word(word = "مَطَرٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // المطر
            Word(word = "ثَلْجٌ", length = 3, diacriticType = DiacriticType.MIXED.name),     // الثلج
            Word(word = "رِيَاحٌ", length = 3, diacriticType = DiacriticType.MIXED.name),    // الرياح
            
            // أربعة أحرف
            Word(word = "مَدْرَسَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "سَيَّارَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "شَجَرَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "غُرْفَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "مَكْتَبَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "حَدِيقَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "طَائِرَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "جَرِيدَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "سَفِينَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "مِرْآةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "كُرْسِيٌّ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "طَاوِلَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "نَافِذَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "دَرَّاجَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            Word(word = "مِظَلَّةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
            
            // خمسة أحرف
            Word(word = "مُسْتَشْفَى", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "جَامِعَةٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "مَطْبَخٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "حَاسُوبٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "تِلْفَازٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "هَاتِفٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "مِصْبَاحٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "سَاعَةٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "مِفْتَاحٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "طَبِيبٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "مُهَنْدِسٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "مُعَلِّمٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "تِلْمِيذٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "صَدِيقٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            Word(word = "جَارٌ", length = 5, diacriticType = DiacriticType.MIXED.name),
            
            // كلمات مع التنوين - متنوعة
            Word(word = "كِتَابًا", length = 3, diacriticType = DiacriticType.TANWEEN.name),
            Word(word = "قَلَمًا", length = 3, diacriticType = DiacriticType.TANWEEN.name),
            Word(word = "بَيْتًا", length = 3, diacriticType = DiacriticType.TANWEEN.name),
            Word(word = "وَلَدًا", length = 3, diacriticType = DiacriticType.TANWEEN.name),
            Word(word = "بَابًا", length = 3, diacriticType = DiacriticType.TANWEEN.name),
            Word(word = "كَلْبًا", length = 3, diacriticType = DiacriticType.TANWEEN.name),
            Word(word = "بَحْرًا", length = 3, diacriticType = DiacriticType.TANWEEN.name),
            Word(word = "شَمْسًا", length = 3, diacriticType = DiacriticType.TANWEEN.name),
            Word(word = "قَمَرًا", length = 3, diacriticType = DiacriticType.TANWEEN.name),
            Word(word = "نَهْرًا", length = 3, diacriticType = DiacriticType.TANWEEN.name)
        )
    }

    private fun getThreeLetterBoostWords(): List<Word> {
        return listOf(
            // فَتْحَة
            Word(word = "كَتَبَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "لَعَبَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "ذَهَبَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "جَلَسَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "خَرَجَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "دَخَلَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "رَكَبَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "سَمَعَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "ضَحَكَ", length = 3, diacriticType = DiacriticType.FATHA.name),
            Word(word = "فَتَحَ", length = 3, diacriticType = DiacriticType.FATHA.name),

            // كَسْرَة
            Word(word = "بِئْرٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "سِحْرٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "ذِهْنٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "نِسْرٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "جِسْرٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "عِرْقٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "مِسْكٌ", length = 3, diacriticType = DiacriticType.KASRA.name),
            Word(word = "نِعْمَ", length = 3, diacriticType = DiacriticType.KASRA.name),

            // ضَمَّة
            Word(word = "نُورٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "رُوحٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "سُوقٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "سُورٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "دُودٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "فُولٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "تُوتٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),
            Word(word = "بُومٌ", length = 3, diacriticType = DiacriticType.DAMMA.name),

            // سُكُون
            Word(word = "قَلْبٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "بَحْرٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "نَهْرٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "لَيْلٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "شَمْسٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "قَمْرٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "سَقْفٌ", length = 3, diacriticType = DiacriticType.SUKOON.name),
            Word(word = "جَنْبٌ", length = 3, diacriticType = DiacriticType.SUKOON.name)
        )
    }
}
