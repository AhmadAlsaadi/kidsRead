# Build Instructions - تعليمات البناء

## متطلبات النظام

### البرامج المطلوبة
1. **Android Studio**: Arctic Fox (2020.3.1) أو أحدث
   - تحميل من: https://developer.android.com/studio
   
2. **JDK**: Java Development Kit 17 أو أحدث
   - عادة يأتي مع Android Studio
   
3. **Android SDK**:
   - API Level 24 (Android 7.0) - الحد الأدنى
   - API Level 34 (Android 14) - المستهدف
   
4. **Gradle**: 8.2 (يتم تنزيله تلقائياً)

### متطلبات الجهاز
- **الذاكرة**: 8 GB RAM (الحد الأدنى)، 16 GB (موصى به)
- **المساحة**: 10 GB مساحة فارغة
- **نظام التشغيل**: Windows 10/11، macOS 10.14+، أو Linux

## خطوات البناء

### 1. استنساخ المشروع
```bash
git clone https://github.com/your-username/kidsRead.git
cd kidsRead
```

### 2. فتح المشروع في Android Studio

#### على macOS:
```bash
open -a "Android Studio" .
```

#### على Windows:
- افتح Android Studio
- File → Open
- حدد مجلد المشروع

#### على Linux:
```bash
studio.sh .
```

### 3. انتظار مزامنة Gradle
- عند فتح المشروع لأول مرة، سيبدأ Android Studio تلقائياً بـ:
  - تنزيل Gradle Wrapper
  - تنزيل التبعيات (Dependencies)
  - مزامنة المشروع
- قد يستغرق هذا 5-10 دقائق

### 4. حل مشاكل المزامنة (إذا حدثت)

#### مشكلة: "SDK not found"
**الحل**:
```bash
# في Android Studio
File → Project Structure → SDK Location
# تأكد من تحديد مسار Android SDK
```

#### مشكلة: "Gradle sync failed"
**الحل**:
```bash
# في Terminal داخل Android Studio
./gradlew clean
./gradlew build --refresh-dependencies
```

#### مشكلة: "Could not resolve dependencies"
**الحل**:
1. تحقق من الاتصال بالإنترنت
2. قد تحتاج لإعداد Proxy إذا كنت خلف جدار حماية
3. جرب:
```bash
./gradlew build --refresh-dependencies --stacktrace
```

### 5. بناء المشروع

#### من Android Studio (الطريقة الموصى بها)
```
Build → Make Project
```
أو اضغط: `Ctrl+F9` (Windows/Linux) أو `Cmd+F9` (macOS)

#### من Terminal
```bash
# Clean build
./gradlew clean build

# Build Debug APK
./gradlew assembleDebug

# Build Release APK
./gradlew assembleRelease
```

### 6. تشغيل التطبيق

#### على محاكي (Emulator)
1. **إنشاء محاكي**:
   - Tools → Device Manager
   - Create Device
   - اختر جهاز (مثل: Pixel 5)
   - اختر System Image (API 34 موصى به)
   - Finish

2. **تشغيل**:
   - اضغط على زر Run ▶️
   - أو: `Shift+F10` (Windows/Linux) أو `Ctrl+R` (macOS)

#### على جهاز حقيقي
1. **تفعيل وضع المطور**:
   - Settings → About Phone
   - اضغط على "Build Number" 7 مرات

2. **تفعيل USB Debugging**:
   - Settings → Developer Options
   - فعّل USB Debugging

3. **توصيل الجهاز**:
   - وصّل الجهاز بالكمبيوتر عبر USB
   - اقبل التصريح على الجهاز

4. **تشغيل**:
   - اختر الجهاز من القائمة
   - اضغط على Run ▶️

## بناء APK للتوزيع

### Debug APK (للتجربة)
```bash
./gradlew assembleDebug
```
الملف في: `app/build/outputs/apk/debug/app-debug.apk`

### Release APK (للنشر)

#### 1. إنشاء Keystore
```bash
keytool -genkey -v -keystore kidsread.keystore -alias kidsread -keyalg RSA -keysize 2048 -validity 10000
```

#### 2. تعديل app/build.gradle
أضف:
```gradle
android {
    signingConfigs {
        release {
            storeFile file("../kidsread.keystore")
            storePassword "your_password"
            keyAlias "kidsread"
            keyPassword "your_password"
        }
    }
    
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

#### 3. بناء Release APK
```bash
./gradlew assembleRelease
```
الملف في: `app/build/outputs/apk/release/app-release.apk`

## الاختبار

### تشغيل Unit Tests
```bash
./gradlew test
```

### تشغيل Instrumentation Tests
```bash
./gradlew connectedAndroidTest
```

### Lint Check
```bash
./gradlew lint
```

## حل المشاكل الشائعة

### 1. خطأ: "Execution failed for task ':app:compileDebugKotlin'"
**الحل**:
```bash
./gradlew clean
File → Invalidate Caches / Restart
```

### 2. خطأ: "Failed to install apk"
**الحل**:
- تأكد من تفعيل USB Debugging
- جرب:
```bash
adb kill-server
adb start-server
```

### 3. خطأ: "Out of memory"
**الحل**:
في `gradle.properties`، زد الذاكرة:
```properties
org.gradle.jvmargs=-Xmx4096m -Dfile.encoding=UTF-8
```

### 4. التطبيق بطيء على المحاكي
**الحل**:
- استخدم محاكي بـ x86/x86_64 (أسرع)
- فعّل Hardware Acceleration
- أو استخدم جهاز حقيقي

## بناء AAB (Android App Bundle) للـ Play Store

```bash
./gradlew bundleRelease
```
الملف في: `app/build/outputs/bundle/release/app-release.aab`

## التحقق من حجم APK

```bash
./gradlew assembleRelease
ls -lh app/build/outputs/apk/release/app-release.apk
```

الحجم المتوقع: 5-10 MB

## الموارد المفيدة

- [Android Developers Documentation](https://developer.android.com/docs)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Room Persistence Library](https://developer.android.com/training/data-storage/room)
- [Material Design Components](https://material.io/develop/android)

## الدعم

إذا واجهت أي مشاكل:
1. تحقق من [Issues على GitHub](https://github.com/your-username/kidsRead/issues)
2. ابحث عن الخطأ على Stack Overflow
3. افتح Issue جديد مع تفاصيل الخطأ

---

**ملاحظة**: تأكد دائماً من استخدام أحدث إصدار من Android Studio والـ SDK للحصول على أفضل تجربة بناء.
