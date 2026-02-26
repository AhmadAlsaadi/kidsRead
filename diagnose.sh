#!/bin/bash

# 🔍 أداة التشخيص - للتحقق من إعدادات Android

echo "🔍 أداة التشخيص - Android Development Environment"
echo "=================================================="
echo ""

# 1. التحقق من Android Studio
echo "1️⃣  التحقق من Android Studio:"
if [ -d "/Applications/Android Studio.app" ]; then
    echo "   ✅ Android Studio مثبت"
    echo "   📍 المسار: /Applications/Android Studio.app"
else
    echo "   ❌ Android Studio غير مثبت"
    echo "   💡 الحل: brew install android-studio"
fi
echo ""

# 2. التحقق من Android SDK
echo "2️⃣  التحقق من Android SDK:"
if [ -d "$HOME/Library/Android/sdk" ]; then
    echo "   ✅ Android SDK موجود"
    echo "   📍 المسار: $HOME/Library/Android/sdk"
    echo "   📦 المحتويات:"
    ls -1 "$HOME/Library/Android/sdk" | grep -E "^(platforms|build-tools|tools|emulator|platform-tools)" | sed 's/^/      • /'
else
    echo "   ❌ Android SDK غير موجود"
    echo "   💡 الحل: Android Studio سينشؤه تلقائياً عند فتحه"
fi
echo ""

# 3. التحقق من Java
echo "3️⃣  التحقق من Java:"
if which java > /dev/null 2>&1; then
    JAVA_VERSION=$(java -version 2>&1 | grep version | head -1)
    echo "   ✅ Java مثبت"
    echo "   📍 الإصدار: $JAVA_VERSION"
else
    echo "   ❌ Java غير مثبت"
    echo "   💡 الحل: Android Studio يوفر Java مدمج"
fi
echo ""

# 4. التحقق من Gradle
echo "4️⃣  التحقق من Gradle Wrapper:"
GRADLE_PATH="/Users/ahmadalsaadi/Documents/gitRepo/kidsRead/gradlew"
if [ -f "$GRADLE_PATH" ]; then
    echo "   ✅ Gradle Wrapper موجود"
    echo "   📍 المسار: $GRADLE_PATH"
else
    echo "   ❌ Gradle Wrapper غير موجود"
fi
echo ""

# 5. التحقق من المشروع
echo "5️⃣  التحقق من مشروع KidsRead:"
PROJECT_PATH="/Users/ahmadalsaadi/Documents/gitRepo/kidsRead"
if [ -d "$PROJECT_PATH" ]; then
    echo "   ✅ المشروع موجود"
    echo "   📍 المسار: $PROJECT_PATH"
    
    if [ -f "$PROJECT_PATH/build.gradle" ]; then
        echo "   ✅ build.gradle موجود"
    fi
    
    if [ -f "$PROJECT_PATH/settings.gradle" ]; then
        echo "   ✅ settings.gradle موجود"
    fi
    
    if [ -f "$PROJECT_PATH/app/build.gradle" ]; then
        echo "   ✅ app/build.gradle موجود"
    fi
else
    echo "   ❌ المشروع غير موجود"
fi
echo ""

# 6. الملخص
echo "=================================================="
echo "📊 الملخص:"
echo ""

if [ -d "/Applications/Android Studio.app" ] && [ -d "$HOME/Library/Android/sdk" ]; then
    echo "✅ كل شيء جاهز! يمكنك البدء:"
    echo ""
    echo "   open -a \"Android Studio\" $PROJECT_PATH"
    echo ""
else
    echo "⚠️  يوجد مشاكل تحتاج حل:"
    
    if [ ! -d "/Applications/Android Studio.app" ]; then
        echo "   1. ثبّت Android Studio: brew install android-studio"
    fi
    
    if [ ! -d "$HOME/Library/Android/sdk" ]; then
        echo "   2. Android Studio سينشئ SDK تلقائياً عند فتحه"
    fi
fi

echo ""
echo "=================================================="
