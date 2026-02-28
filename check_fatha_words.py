#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import json
import re

# قراءة الملف
with open('/Users/ahmadalsaadi/Documents/gitRepo/kidsRead/docs/data/words.json', 'r', encoding='utf-8') as f:
    words = json.load(f)

# الحركات العربية
FATHA = '\u064E'  # َ
KASRA = '\u0650'  # ِ
DAMMA = '\u064F'  # ُ
SUKOON = '\u0652'  # ْ
SHADDA = '\u0651'  # ّ
TANWEEN_FATH = '\u064B'  # ً
TANWEEN_KASR = '\u064D'  # ٍ
TANWEEN_DAMM = '\u064C'  # ٌ

# الحروف العربية (بدون ألف مقصورة وهمزة)
ARABIC_LETTERS = 'ابتثجحخدذرزسشصضطظعغفقكلمنهويء'

def is_arabic_letter(char):
    """تحقق: هل هذا حرف عربي؟"""
    return '\u0621' <= char <= '\u064A' and char not in [FATHA, KASRA, DAMMA, SUKOON, SHADDA, TANWEEN_FATH, TANWEEN_KASR, TANWEEN_DAMM]

def check_word_has_only_fatha(word):
    """
    تحقق: هل كل حرف في الكلمة يحمل فتحة فقط؟
    يجب أن لا يكون هناك:
    - شدة
    - سكون
    - كسرة أو ضمة
    - ألف مقصورة (ى)
    - حروف بدون حركة
    """
    issues = []
    
    # إزالة الحركات لحساب عدد الأحرف الفعلي
    letters_only = ''.join([c for c in word if is_arabic_letter(c)])
    
    # التحقق من أن الكلمة 3 أحرف
    if len(letters_only) != 3:
        issues.append(f"عدد الأحرف {len(letters_only)} وليس 3")
    
    # التحقق من عدم وجود ألف مقصورة
    if 'ى' in word:
        issues.append("يحتوي على ألف مقصورة (ى)")
    
    # التحقق من عدم وجود شدة
    if SHADDA in word:
        issues.append("يحتوي على شدة (ّ)")
    
    # التحقق من عدم وجود سكون
    if SUKOON in word:
        issues.append("يحتوي على سكون (ْ)")
    
    # التحقق من عدم وجود كسرة
    if KASRA in word:
        issues.append("يحتوي على كسرة (ِ)")
    
    # التحقق من عدم وجود ضمة
    if DAMMA in word:
        issues.append("يحتوي على ضمة (ُ)")
    
    # التحقق من عدم وجود تنوين
    if TANWEEN_FATH in word or TANWEEN_KASR in word or TANWEEN_DAMM in word:
        issues.append("يحتوي على تنوين")
    
    # التحقق من أن كل حرف لديه فتحة
    i = 0
    letter_count = 0
    while i < len(word):
        char = word[i]
        if is_arabic_letter(char):
            letter_count += 1
            # التحقق من أن الحرف التالي هو فتحة
            if i + 1 < len(word) and word[i + 1] == FATHA:
                i += 2  # تخطي الحرف والفتحة
            else:
                # الحرف الأخير قد لا يحتوي على فتحة (مقبول)
                if letter_count < 3:
                    issues.append(f"الحرف '{char}' في الموضع {letter_count} بدون فتحة")
                i += 1
        else:
            i += 1
    
    return len(issues) == 0, issues

# فحص الكلمات الثلاثية ذات diacriticType = FATHA
print("=" * 80)
print("فحص الكلمات الثلاثية بحركة الفتحة")
print("=" * 80)

three_letter_fatha_words = [w for w in words if w['length'] == 3 and w['diacriticType'] == 'FATHA']
print(f"\nإجمالي الكلمات الثلاثية بـ diacriticType='FATHA': {len(three_letter_fatha_words)}")

problematic_words = []
correct_words = []

for word_obj in three_letter_fatha_words:
    word = word_obj['word']
    is_valid, issues = check_word_has_only_fatha(word)
    
    if not is_valid:
        problematic_words.append((word_obj['id'], word, issues))
    else:
        correct_words.append((word_obj['id'], word))

print(f"\n✅ كلمات صحيحة: {len(correct_words)}")
print(f"❌ كلمات بها مشاكل: {len(problematic_words)}")

if problematic_words:
    print("\n" + "=" * 80)
    print("الكلمات التي بها مشاكل:")
    print("=" * 80)
    for word_id, word, issues in problematic_words:
        print(f"\nID {word_id}: {word}")
        for issue in issues:
            print(f"  - {issue}")

# التحقق من التكرار
print("\n" + "=" * 80)
print("التحقق من التكرار")
print("=" * 80)

word_text_list = [w for _, w in correct_words]
duplicates = [w for w in word_text_list if word_text_list.count(w) > 1]
unique_duplicates = list(set(duplicates))

if unique_duplicates:
    print(f"❌ وجدنا {len(unique_duplicates)} كلمات مكررة:")
    for dup in unique_duplicates:
        ids = [id for id, w in correct_words if w == dup]
        print(f"  - {dup}: IDs {ids}")
else:
    print("✅ لا يوجد تكرار في الكلمات الصحيحة")

print("\n" + "=" * 80)
print(f"الخلاصة: نحتاج حذف {len(problematic_words)} كلمة وإضافة {len(problematic_words)} كلمة جديدة")
print("=" * 80)
