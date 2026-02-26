# 🔧 حل مشكلة SDK Path في Android Studio

## ⚠️ المشكلة
```
Android Studio asks for SDK path when running
أو
"Unable to find SDK"
```

---

## ✅ الحل السريع (5 دقائق)

### الطريقة 1: من داخل Android Studio (الأسهل)

#### الخطوة 1: افتح Settings
```
Android Studio → Preferences (macOS)
أو
File → Settings (Windows/Linux)
```

#### الخطوة 2: اذهب إلى SDK Settings
```
Appearance & Behavior → System Settings → Android SDK
```

#### الخطوة 3: تحقق من SDK Location
- يجب أن تشير إلى: `/Users/USERNAME/Library/Android/sdk`
- إذا كانت فارغة، اختر المجلد

#### الخطوة 4: اضغط Apply ثم OK

---

### الطريقة 2: التثبيت التلقائي

إذا لم يكن لديك Android SDK:

1. في Android Studio الرئيسية:
```
Tools → SDK Manager
```

2. اختر نسخة Android (مثلاً Android 14 - API 34)

3. اضغط Install

4. انتظر حتى ينتهي التثبيت

---

## 🔍 تحديد مسار SDK على macOS

### طريقة 1: استخدام Terminal

```bash
# تحقق من المسار
ls ~/Library/Android/sdk

# إذا لم يكن موجوداً، Android Studio سينشؤه تلقائياً
```

### طريقة 2: يدويً

```bash
# إذا كنت تريد نقل SDK من مكان آخر
cp -r /path/to/old/sdk ~/Library/Android/sdk
```

---

## 📝 خطوات تفصيلية للحل الكامل

### 1. افتح Android Studio
```bash
open -a "Android Studio"
```

### 2. من الشاشة الترحيبية
```
الخيار: "More Actions" → "SDK Manager"
أو
File → Settings → Android SDK
```

### 3. في نافذة SDK Manager

```
انظر إلى الأعلى: "Android SDK Location"

المسار الصحيح على macOS:
/Users/[YOUR_USERNAME]/Library/Android/sdk

المسار الصحيح على Windows:
C:\Users\[YOUR_USERNAME]\AppData\Local\Android\sdk

المسار الصحيح على Linux:
/home/[YOUR_USERNAME]/Android/sdk
```

### 4. إذا كان المسار فارغاً

**اضغط على أيقونة المجلد وحدد:**
```
macOS:  ~/Library/Android/sdk
Windows: C:\Users\[USERNAME]\AppData\Local\Android\sdk
Linux:   ~/Android/sdk
```

### 5. تحقق من المكونات المثبتة

```
في نفس النافذة، اختر:
- ✅ API 34 (أو أحدث)
- ✅ Build Tools
- ✅ Android Emulator (إذا أردت محاكي)
- ✅ Android SDK Platform-Tools
```

### 6. اضغط Apply ثم OK

---

## 🛠️ إذا استمرت المشكلة

### الحل الجذري: إعادة تثبيت Android Studio

```bash
# 1. احذف Android Studio
rm -rf /Applications/Android\ Studio.app

# 2. احذف SDK (اختياري إذا كنت تريد بدء نظيف)
rm -rf ~/Library/Android

# 3. أعد التثبيت
brew install android-studio

# 4. افتح مرة أخرى
open -a "Android Studio"
```

---

## ✅ التحقق من النجاح

بعد تعيين المسار:

1. **في Android Studio:**
```
File → Project Structure → SDK Location
```
يجب أن ترى المسار الصحيح ✓

2. **في Terminal:**
```bash
ls ~/Library/Android/sdk

# يجب أن ترى:
# build-tools/
# platforms/
# tools/
# emulator/
# platform-tools/
```

3. **حاول تشغيل المشروع:**
```
اضغط Run ▶️
يجب أن يعمل الآن!
```

---

## 🆘 مشاكل إضافية وحلولها

### مشكلة: "SDK Tools not found"
```bash
# الحل:
# 1. فتح Tools → SDK Manager
# 2. اختر "SDK Tools" tab
# 3. ثبّت:
#    - Android SDK Build-Tools
#    - Android SDK Platform-Tools
#    - Android Emulator
```

### مشكلة: "No Android Virtual Device (AVD)"
```bash
# الحل:
# 1. Tools → Device Manager
# 2. اضغط Create Device
# 3. اختر Pixel 5
# 4. اختر API 34
# 5. اضغط Finish
```

### مشكلة: "Gradle Sync Failed"
```bash
# الحل:
cd /Users/ahmadalsaadi/Documents/gitRepo/kidsRead
./gradlew clean
./gradlew --refresh-dependencies

# ثم في Android Studio:
File → Invalidate Caches / Restart
```

---

## 📋 قائمة التحقق

- [ ] فتحت Android Studio
- [ ] ذهبت إلى SDK Manager (Tools → SDK Manager)
- [ ] رأيت مسار SDK (يجب أن يشير إلى ~/Library/Android/sdk)
- [ ] ثبّتت API 34 (أو أحدث)
- [ ] ثبّتت Build Tools
- [ ] ثبّتت Android SDK Platform-Tools
- [ ] أغلقت النافذة بـ Apply/OK
- [ ] فتحت مشروع KidsRead
- [ ] انتظرت Gradle Sync
- [ ] اضغط Run ▶️

---

## 🎯 الملخص السريع

```
المشكلة:   Android Studio لا يجد SDK
الحل:      1. Tools → SDK Manager
           2. تحقق من مسار SDK
           3. إذا فارغ، حدد: ~/Library/Android/sdk
           4. ثبّت المكونات المطلوبة
           5. اضغط Apply/OK
النتيجة:   ✅ المشروع يعمل!
```

---

## 💡 نصائح مهمة

✅ **المسار الصحيح على macOS:**
```
/Users/ahmadalsaadi/Library/Android/sdk
```

✅ **لا تغيّر مسار SDK بعد التثبيت** - قد يسبب مشاكل

✅ **اترك Android Studio يثبّت الحزم** - لا تحاول يدويً

✅ **إذا أخطأت، استخدم Invalidate Cache**:
```
File → Invalidate Caches / Restart → Invalidate and Restart
```

---

## 📞 إذا استمرت المشكلة

جرب هذا الأمر في Terminal:

```bash
# 1. احذف مجلد Android SDK
rm -rf ~/Library/Android/sdk

# 2. افتح Android Studio
open -a "Android Studio"

# 3. اتبع الخطوات الأولى مرة أخرى
# Android Studio سينشئ مجلد جديد تلقائياً
```

---

## ✨ بعد حل المشكلة

```bash
# افتح مشروع KidsRead
open -a "Android Studio" /Users/ahmadalsaadi/Documents/gitRepo/kidsRead

# انتظر Gradle Sync
# اضغط Run ▶️
# استمتع! 🎊
```

---

**المشكلة يجب أن تُحل الآن! إذا استمرت، أخبرني بتفاصيل الخطأ بالضبط.**
