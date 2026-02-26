#!/bin/bash

# 🚀 سكريبت الإعداد التلقائي لـ KidsRead

echo "🚀 بدء الإعداد التلقائي لـ KidsRead"
echo "===================================="
echo ""

# الألوان
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m'

# 1. فتح Android Studio مع المشروع
echo -e "${YELLOW}📂 فتح Android Studio مع المشروع...${NC}"
open -a "Android Studio" /Users/ahmadalsaadi/Documents/gitRepo/kidsRead

echo ""
echo -e "${GREEN}✅ تم فتح Android Studio${NC}"
echo ""

# 2. انتظر قليلاً
sleep 3

# 3. تعليمات
echo -e "${YELLOW}📋 الخطوات التالية:${NC}"
echo ""
echo "1️⃣  انتظر Android Studio ليفتح تماماً (30 ثانية)"
echo "2️⃣  إذا طلب SDK، اضغط [Download SDK]"
echo "3️⃣  انتظر التحميل (10-15 دقيقة)"
echo "4️⃣  بعد الانتهاء، اضغط Run ▶️"
echo "5️⃣  اختر Emulator أو Device"
echo ""

echo -e "${GREEN}=========================${NC}"
echo -e "${GREEN}✨ تمتع بالتطبيق! 🎉${NC}"
echo -e "${GREEN}=========================${NC}"
echo ""

# 4. معلومات إضافية
echo -e "${YELLOW}💡 نصائح:${NC}"
echo "   • إذا رأيت 'SDK path' → اضغط Download"
echo "   • إذا توقف التحميل → أعد فتح Android Studio"
echo "   • المشروع كامل وجاهز ✅"
echo ""

# 5. فتح ملف الإرشادات
echo -e "${YELLOW}📖 فتح دليل الإعداد...${NC}"
open /Users/ahmadalsaadi/Documents/gitRepo/kidsRead/SDK_SETUP_AR.md

echo -e "${GREEN}✅ تم!${NC}"
