# 📱 دليل تثبيت وتشغيل KidsRead

## المشكلة الحالية
```
⚠️ Java Development Kit (JDK) غير مثبت بشكل صحيح
⚠️ Android SDK غير مثبت
⚠️ لا يمكن بناء المشروع من Terminal مباشرة
```

---

## ✅ الحل: استخدام Android Studio

Android Studio يأتي مع كل شيء مثبت:
- ✅ JDK مدمج
- ✅ Android SDK مدمج
- ✅ Gradle Wrapper جاهز
- ✅ محاكي مدمج

---

## 🚀 خطوات التشغيل من Android Studio

### الخطوة 1: تثبيت Android Studio
**الموقع**: https://developer.android.com/studio

```bash
# على macOS باستخدام Homebrew (الأسهل)
brew install android-studio
```

أو قم بتحميل المثبت مباشرة من الموقع.

### الخطوة 2: فتح المشروع

```bash
# افتح Android Studio ثم
File → Open → اختر المجلد:
/Users/ahmadalsaadi/Documents/gitRepo/kidsRead
```

أو من Terminal:
```bash
open -a "Android Studio" /Users/ahmadalsaadi/Documents/gitRepo/kidsRead
```

### الخطوة 3: انتظر Gradle Sync
- Android Studio سيقوم تلقائياً بـ:
  - تحميل Gradle Wrapper
  - تحميل جميع المكتبات
  - مزامنة المشروع
- ⏱️ قد يستغرق 5-15 دقيقة في المرة الأولى

### الخطوة 4: إنشاء محاكي (Emulator)

```
Tools → Device Manager → Create Device
├─ Select Phone: Pixel 5
├─ Select System Image: Android 14 (API 34)
└─ Finish
```

### الخطوة 5: بدء التشغيل

```
▶️ اضغط Run
أو اضغط Shift+F10
```

---

## 📦 متطلبات التثبيت

### للنظام (macOS)
```bash
# تثبيت Xcode Command Line Tools
xcode-select --install

# تثبيت Homebrew (إذا لم يكن مثبتاً)
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# تثبيت Android Studio
brew install android-studio
```

### للمشروع (تلقائي)
- ✅ Java 17+ (مدمج في Android Studio)
- ✅ Android SDK (مدمج في Android Studio)
- ✅ Gradle 8.2 (مدمج في المشروع)

---

## 🔧 خيارات بديلة

### خيار 1: استخدام Emulator من Command Line (بعد التثبيت)

```bash
# إنشاء محاكي
android create avd -n KidsReadEmulator -t android-34 -c 512M

# تشغيل المحاكي
emulator -avd KidsReadEmulator &

# بناء وتثبيت التطبيق
cd /Users/ahmadalsaadi/Documents/gitRepo/kidsRead
./gradlew installDebug
```

### خيار 2: اختبار على جهاز حقيقي

```bash
# تشغيل USB Debugging على الجهاز:
Settings → Developer Options → USB Debugging (On)

# ثم قم بتشغيل:
./gradlew installDebug
```

---

## 🐛 حل المشاكل الشائعة

### مشكلة: "Gradle sync failed"
```bash
cd /Users/ahmadalsaadi/Documents/gitRepo/kidsRead
./gradlew clean
./gradlew --refresh-dependencies
```

ثم في Android Studio:
```
File → Invalidate Caches / Restart
```

### مشكلة: "SDK not found"
```
File → Project Structure → SDK Location
تأكد من تحديد مسار Android SDK بشكل صحيح
```

### مشكلة: المحاكي بطيء
- استخدم محاكي x86_64 بدلاً من ARM
- فعّل Hardware Acceleration
- جرب جهاز حقيقي

---

## 📋 قائمة التحقق

- [ ] Android Studio مثبت
- [ ] JDK 17+ متوفر (في Android Studio)
- [ ] Android SDK مثبت
- [ ] المشروع يتم فتحه بسلام
- [ ] Gradle Sync اكتمل
- [ ] محاكي تم إنشاؤه (أو جهاز حقيقي)
- [ ] التطبيق يعمل

---

## 🎯 الخطوات السريعة

**للمستخدمين العجلة:**

```bash
# 1. تثبيت Android Studio (واحد مرة فقط)
brew install android-studio

# 2. فتح المشروع
open -a "Android Studio" /Users/ahmadalsaadi/Documents/gitRepo/kidsRead

# 3. انتظر Gradle Sync ثم اضغط Run ▶️
```

---

## 📞 الدعم الإضافي

إذا حصلت على أي مشكلة:

1. **راجع:** BUILD.md (تعليمات بناء تفصيلية)
2. **راجع:** QUICK_START.md (بدء سريع)
3. **راجع:** USER_GUIDE.md (دليل الاستخدام)

---

## ✨ الملخص

**المشروع جاهز 100% لكن يحتاج:**
1. Android Studio ليتم تثبيته
2. فتح المشروع فيه
3. انتظار Gradle Sync
4. الضغط على Run ▶️

**الخبر السار:** Android Studio يتولى كل شيء تلقائياً! ✨

---

**بعد التثبيت، اتبع QUICK_START.md لبدء سريع!**
