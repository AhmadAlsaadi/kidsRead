#!/bin/bash

# فحص شامل لمشروع KidsRead

echo "🔍 بدء الفحص الشامل للمشروع..."
echo ""

# الألوان
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# عداد الأخطاء
ERRORS=0

# دالة للتحقق من الملفات
check_file() {
    if [ -f "$1" ]; then
        echo -e "${GREEN}✅${NC} $1"
    else
        echo -e "${RED}❌${NC} $1"
        ERRORS=$((ERRORS + 1))
    fi
}

# دالة للتحقق من المجلدات
check_dir() {
    if [ -d "$1" ]; then
        echo -e "${GREEN}✅${NC} $1"
    else
        echo -e "${RED}❌${NC} $1"
        ERRORS=$((ERRORS + 1))
    fi
}

# فحص البنية الأساسية
echo "📁 فحص البنية الأساسية..."
check_dir "app"
check_dir "app/src"
check_dir "app/src/main"
check_dir "gradle"
echo ""

# فحص ملفات Gradle
echo "⚙️  فحص ملفات Gradle..."
check_file "build.gradle"
check_file "settings.gradle"
check_file "gradle.properties"
check_file "app/build.gradle"
check_file "gradlew"
echo ""

# فحص AndroidManifest
echo "📄 فحص ملفات البيان..."
check_file "app/src/main/AndroidManifest.xml"
echo ""

# فحص ملفات Kotlin الأساسية
echo "🎯 فحص ملفات Kotlin الأساسية..."
check_file "app/src/main/java/com/kidsread/app/MainActivity.kt"
check_file "app/src/main/java/com/kidsread/app/SettingsActivity.kt"
check_file "app/src/main/java/com/kidsread/app/KidsReadApplication.kt"
echo ""

# فحص المجلدات
echo "📂 فحص مجلدات الكود..."
check_dir "app/src/main/java/com/kidsread/app/algorithm"
check_dir "app/src/main/java/com/kidsread/app/data"
check_dir "app/src/main/java/com/kidsread/app/ui"
check_dir "app/src/main/java/com/kidsread/app/utils"
echo ""

# فحص ملفات Resources
echo "🎨 فحص ملفات Resources..."
check_dir "app/src/main/res/layout"
check_dir "app/src/main/res/values"
check_file "app/src/main/res/layout/activity_main.xml"
check_file "app/src/main/res/layout/activity_settings.xml"
check_file "app/src/main/res/values/strings.xml"
check_file "app/src/main/res/values/colors.xml"
check_file "app/src/main/res/values/themes.xml"
echo ""

# فحص ملفات التوثيق
echo "📚 فحص ملفات التوثيق..."
check_file "README.md"
check_file "USER_GUIDE.md"
check_file "BUILD.md"
check_file "ADDING_WORDS.md"
check_file "QUICK_START.md"
echo ""

# ملخص النتائج
echo "═══════════════════════════════════"
if [ $ERRORS -eq 0 ]; then
    echo -e "${GREEN}✅ جميع الملفات موجودة!${NC}"
    echo "المشروع جاهز للتشغيل"
else
    echo -e "${RED}❌ تم العثور على $ERRORS أخطاء${NC}"
fi
echo "═══════════════════════════════════"
