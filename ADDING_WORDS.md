# دليل إضافة كلمات جديدة

## كيفية إضافة المزيد من الكلمات للتطبيق

### الطريقة 1: تعديل ملف WordsDataSource.kt

يمكنك إضافة كلمات جديدة مباشرة في الكود:

```kotlin
// في ملف: app/src/main/java/com/kidsread/app/data/WordsDataSource.kt

private fun getAllWords(): List<Word> {
    return listOf(
        // أضف كلماتك هنا
        Word(word = "كَلِمَة", length = 3, diacriticType = DiacriticType.MIXED.name),
        Word(word = "جَدِيد", length = 3, diacriticType = DiacriticType.MIXED.name),
        // ... المزيد من الكلمات
    )
}
```

### معلمات الكلمة

#### word (String)
الكلمة بالتشكيل الكامل. أمثلة:
- `"بَ"` - حرف واحد بالفتحة
- `"كِتَابٌ"` - كلمة كاملة بالتشكيل

#### length (Int)
عدد الأحرف في الكلمة (بدون التشكيل):
- `1` - حرف واحد
- `2` - حرفان
- `3` - ثلاثة أحرف
- `4` - أربعة أحرف
- `5` - خمسة أحرف

#### diacriticType (String)
نوع التشكيل المستخدم:
- `DiacriticType.FATHA.name` - الفتحة فقط (َ)
- `DiacriticType.KASRA.name` - الكسرة فقط (ِ)
- `DiacriticType.DAMMA.name` - الضمة فقط (ُ)
- `DiacriticType.SUKOON.name` - السكون فقط (ْ)
- `DiacriticType.TANWEEN.name` - التنوين (ً، ٍ، ٌ)
- `DiacriticType.MIXED.name` - مختلط (أكثر من نوع)

## أمثلة عملية

### مثال 1: إضافة أحرف مفردة
```kotlin
Word(word = "حَ", length = 1, diacriticType = DiacriticType.FATHA.name),
Word(word = "خِ", length = 1, diacriticType = DiacriticType.KASRA.name),
Word(word = "ذُ", length = 1, diacriticType = DiacriticType.DAMMA.name),
```

### مثال 2: إضافة كلمات بحرفين
```kotlin
Word(word = "يَدٌ", length = 2, diacriticType = DiacriticType.MIXED.name),
Word(word = "فَمٌ", length = 2, diacriticType = DiacriticType.MIXED.name),
Word(word = "مَاءٌ", length = 2, diacriticType = DiacriticType.MIXED.name),
```

### مثال 3: إضافة كلمات أطول
```kotlin
Word(word = "مَكْتَبَةٌ", length = 4, diacriticType = DiacriticType.MIXED.name),
Word(word = "مُسْتَشْفَى", length = 5, diacriticType = DiacriticType.MIXED.name),
```

## نصائح لإضافة كلمات فعالة

### 1. التنوع في مستويات الصعوبة
- ابدأ بأحرف مفردة
- انتقل تدريجياً للكلمات الأطول
- نوّع في أشكال التشكيل

### 2. اختيار كلمات مألوفة
- استخدم كلمات من الحياة اليومية
- اختر كلمات يعرفها الطفل بالفعل
- ابتعد عن الكلمات المعقدة أو النادرة

### 3. تصنيف حسب الموضوعات
يمكنك تنظيم الكلمات حسب فئات:
```kotlin
// أجزاء الجسم
Word(word = "يَدٌ", length = 2, diacriticType = DiacriticType.MIXED.name),
Word(word = "رِجْلٌ", length = 3, diacriticType = DiacriticType.MIXED.name),

// الحيوانات
Word(word = "قِطَّةٌ", length = 3, diacriticType = DiacriticType.MIXED.name),
Word(word = "كَلْبٌ", length = 3, diacriticType = DiacriticType.MIXED.name),

// الألوان
Word(word = "أَحْمَرُ", length = 4, diacriticType = DiacriticType.MIXED.name),
Word(word = "أَزْرَقُ", length = 4, diacriticType = DiacriticType.MIXED.name),
```

## إعادة تعيين قاعدة البيانات

إذا أردت حذف البيانات الحالية وإعادة ملء القاعدة بالكلمات الجديدة:

### الطريقة 1: حذف التطبيق وإعادة تثبيته
- احذف التطبيق من الجهاز
- أعد بناء المشروع وتثبيته

### الطريقة 2: تعديل الكود ليحذف البيانات القديمة
في ملف `KidsReadApplication.kt`:
```kotlin
override fun onCreate() {
    super.onCreate()
    
    CoroutineScope(Dispatchers.IO).launch {
        val database = AppDatabase.getDatabase(applicationContext)
        
        // احذف جميع الكلمات القديمة (اختياري)
        // database.wordDao().deleteAllWords()
        
        WordsDataSource.populateDatabase(database.wordDao())
    }
}
```

## الطريقة 2: إضافة كلمات من خلال JSON (متقدم)

يمكنك إنشاء ملف JSON وتحميل الكلمات منه:

### 1. أنشئ ملف JSON
```json
// في: app/src/main/assets/words.json
[
  {
    "word": "كِتَابٌ",
    "length": 3,
    "diacriticType": "MIXED"
  },
  {
    "word": "قَلَمٌ",
    "length": 3,
    "diacriticType": "MIXED"
  }
]
```

### 2. أضف كود لقراءة JSON
```kotlin
// في WordsDataSource.kt
fun loadWordsFromJson(context: Context): List<Word> {
    val json = context.assets.open("words.json")
        .bufferedReader()
        .use { it.readText() }
    
    val gson = Gson()
    val wordsList = gson.fromJson(json, Array<Word>::class.java)
    return wordsList.toList()
}
```

## أدوات مساعدة لكتابة التشكيل

### لوحات مفاتيح مقترحة:
- **Android**: لوحة المفاتيح العربية القياسية
- **Windows**: استخدم الاختصارات:
  - Shift + Q = َ (فتحة)
  - Shift + A = ِ (كسرة)
  - Shift + E = ُ (ضمة)
  - Shift + X = ْ (سكون)
  - Shift + W = ً (تنوين فتح)

### مواقع مفيدة:
- [Tashkeel - أداة تشكيل النصوص](https://tahadz.com/mishkal)
- [موقع التشكيل التلقائي](https://www.shakl.tech)

## المساهمة بالكلمات

إذا أردت مشاركة قائمة كلمات مع المجتمع:
1. أنشئ ملف نصي بالكلمات
2. اتبع التنسيق الموضح أعلاه
3. أرسل Pull Request على GitHub
4. أو شارك القائمة في Issues

---

**نصيحة**: ابدأ بـ 50-100 كلمة في كل فئة، ثم أضف المزيد تدريجياً بناءً على احتياجات الطفل.
