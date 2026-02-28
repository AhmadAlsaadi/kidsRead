/**
 * صفحة الإعدادات
 */

class SettingsPage {
    constructor() {
        this.settings = StorageManager.loadSettings();
        this.diacriticTypes = ['FATHA', 'KASRA', 'DAMMA', 'SUKOON', 'TANWEEN'];
        this.allWords = [];
        this.init();
    }

    init() {
        this.initElements();
        this.loadWords();
        this.loadSettings();
        this.bindEvents();
    }

    async loadWords() {
        // تحميل جميع الكلمات من JSON
        try {
            const response = await fetch('data/words.json');
            this.allWords = await response.json();
        } catch (error) {
            console.error('خطأ في تحميل الكلمات:', error);
            this.allWords = [];
        }
    }

    initElements() {
        this.elements = {
            wordLength: document.getElementById('wordLength'),
            bgColor: document.getElementById('bgColor'),
            wordColor: document.getElementById('wordColor'),
            fontSize: document.getElementById('fontSize'),
            fontSizeValue: document.getElementById('fontSizeValue'),
            fontFamily: document.getElementById('fontFamily'),
            fontPreview: document.getElementById('fontPreview'),
            showStats: document.getElementById('showStats'),
            saveBtn: document.getElementById('saveBtn'),
            resetBtn: document.getElementById('resetBtn'),
            clearDataBtn: document.getElementById('clearDataBtn'),
            backBtn: document.getElementById('backBtn'),
            wordsCountByLength: document.getElementById('wordsCountByLength'),
            availableWordsCount: document.getElementById('availableWordsCount'),
            letterDiacriticsGroups: []
        };

        this.elements.letterDiacriticsGroups = Array.from(
            document.querySelectorAll('.letter-diacritic-group')
        );
    }

    loadSettings() {
        // تحميل القيم الحالية
        this.elements.wordLength.value = this.settings.wordLength;
        this.elements.bgColor.value = this.settings.backgroundColor;
        this.elements.wordColor.value = this.settings.wordColor;
        this.elements.fontSize.value = this.settings.fontSize;
        this.elements.fontSizeValue.textContent = `${this.settings.fontSize}px`;
        this.elements.fontFamily.value = this.settings.fontFamily || "'Arial', sans-serif";
        this.elements.showStats.checked = this.settings.showStats;

        // تطبيق الخط على العرض التجريبي
        this.updateFontPreview();

        this.applyPerLetterDiacriticsToUI();
        this.updateLetterGroupsVisibility();
        this.updateWordCountByLength();
        this.updateAvailableWordsCount();
    }

    bindEvents() {
        // تحديث قيمة حجم الخط
        this.elements.fontSize.addEventListener('input', (e) => {
            this.elements.fontSizeValue.textContent = `${e.target.value}px`;
        });

        // تحديث العرض التجريبي للخط
        this.elements.fontFamily.addEventListener('change', () => {
            this.updateFontPreview();
        });

        this.elements.wordLength.addEventListener('change', () => {
            this.updateLetterGroupsVisibility();
        });

        // إضافة event listeners لجميع checkboxes الحركات لتحديث عداد الكلمات
        this.elements.letterDiacriticsGroups.forEach(group => {
            group.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
                checkbox.addEventListener('change', () => {
                    this.updateAvailableWordsCount();
                });
            });
        });

        // زر الحفظ
        this.elements.saveBtn.addEventListener('click', () => this.saveSettings());

        // زر إعادة التعيين
        this.elements.resetBtn.addEventListener('click', () => this.resetSettings());

        // زر مسح البيانات
        this.elements.clearDataBtn.addEventListener('click', () => this.clearData());

        // زر الرجوع
        this.elements.backBtn.addEventListener('click', () => {
            window.location.href = 'index.html';
        });
    }

    updateFontPreview() {
        const fontFamily = this.elements.fontFamily.value;
        this.elements.fontPreview.style.fontFamily = fontFamily;
    }

    saveSettings() {
        const perLetterDiacritics = this.collectPerLetterDiacritics();
        const currentLength = parseInt(this.elements.wordLength.value);
        const hasEmptyPosition = perLetterDiacritics
            .slice(0, currentLength)
            .some(list => !Array.isArray(list) || list.length === 0);

        if (hasEmptyPosition) {
            alert('يجب اختيار حركة واحدة على الأقل لكل حرف ظاهر.');
            return;
        }

        // تحديث الإعدادات
        this.settings = {
            wordLength: parseInt(this.elements.wordLength.value),
            perLetterDiacritics: perLetterDiacritics,
            backgroundColor: this.elements.bgColor.value,
            wordColor: this.elements.wordColor.value,
            fontSize: parseInt(this.elements.fontSize.value),
            fontFamily: this.elements.fontFamily.value,
            showStats: this.elements.showStats.checked
        };

        // حفظ في LocalStorage
        if (StorageManager.saveSettings(this.settings)) {
            // إظهار رسالة النجاح
            this.showMessage('تم حفظ الإعدادات بنجاح! ✅');
            
            // العودة للصفحة الرئيسية بعد ثانيتين
            setTimeout(() => {
                window.location.href = 'index.html';
            }, 1500);
        } else {
            alert('حدث خطأ في حفظ الإعدادات. حاول مرة أخرى.');
        }
    }

    resetSettings() {
        if (confirm('هل أنت متأكد من إعادة تعيين الإعدادات إلى القيم الافتراضية؟')) {
            StorageManager.resetSettings();
            this.settings = StorageManager.loadSettings();
            this.loadSettings();
            this.showMessage('تم إعادة تعيين الإعدادات! 🔄');
        }
    }

    applyPerLetterDiacriticsToUI() {
        const perLetter = this.getPerLetterDiacritics();

        this.elements.letterDiacriticsGroups.forEach(group => {
            const position = parseInt(group.dataset.position, 10);
            const allowed = perLetter[position - 1] || [];
            this.diacriticTypes.forEach(type => {
                const checkbox = group.querySelector(`#letter${position}_${type}`);
                if (checkbox) {
                    checkbox.checked = allowed.includes(type);
                }
            });
        });
    }

    getPerLetterDiacritics() {
        if (Array.isArray(this.settings.perLetterDiacritics)) {
            return this.normalizePerLetterDiacritics(this.settings.perLetterDiacritics);
        }

        if (Array.isArray(this.settings.selectedDiacritics) && this.settings.selectedDiacritics.length > 0) {
            return this.normalizePerLetterDiacritics([
                this.settings.selectedDiacritics,
                this.settings.selectedDiacritics,
                this.settings.selectedDiacritics,
                this.settings.selectedDiacritics,
                this.settings.selectedDiacritics
            ]);
        }

        const all = [...this.diacriticTypes];
        return this.normalizePerLetterDiacritics([all, all, all, all, all]);
    }

    normalizePerLetterDiacritics(perLetterDiacritics) {
        const normalized = [];
        for (let i = 0; i < 5; i++) {
            const list = Array.isArray(perLetterDiacritics[i]) ? perLetterDiacritics[i] : [];
            normalized.push(list.filter(type => this.diacriticTypes.includes(type)));
        }
        return normalized;
    }

    collectPerLetterDiacritics() {
        const perLetter = [[], [], [], [], []];

        this.elements.letterDiacriticsGroups.forEach(group => {
            const position = parseInt(group.dataset.position, 10);
            const selected = [];
            this.diacriticTypes.forEach(type => {
                const checkbox = group.querySelector(`#letter${position}_${type}`);
                if (checkbox && checkbox.checked) {
                    selected.push(type);
                }
            });
            perLetter[position - 1] = selected;
        });

        return perLetter;
    }

    updateLetterGroupsVisibility() {
        const length = parseInt(this.elements.wordLength.value, 10);
        this.elements.letterDiacriticsGroups.forEach(group => {
            const position = parseInt(group.dataset.position, 10);
            group.style.display = position <= length ? 'block' : 'none';
        });
        
        // تحديث عدد الكلمات المتوفرة عند تغيير طول الكلمة
        this.updateWordCountByLength();
        this.updateAvailableWordsCount();
    }

    updateWordCountByLength() {
        const wordLength = parseInt(this.elements.wordLength.value, 10);
        
        // حساب عدد الكلمات بهذا الطول فقط
        const wordsByLength = this.allWords.filter(word => word.length === wordLength);
        
        // تحديث العرض
        if (this.elements.wordsCountByLength) {
            this.elements.wordsCountByLength.textContent = wordsByLength.length;
            
            // تغيير اللون بناءً على العدد
            if (wordsByLength.length === 0) {
                this.elements.wordsCountByLength.style.color = '#ff6b6b';
            } else if (wordsByLength.length < 50) {
                this.elements.wordsCountByLength.style.color = '#ffa500';
            } else {
                this.elements.wordsCountByLength.style.color = '#667eea';
            }
        }
    }

    updateAvailableWordsCount() {
        const wordLength = parseInt(this.elements.wordLength.value, 10);
        const perLetterDiacritics = this.collectPerLetterDiacritics();
        
        // فلترة الكلمات بناءً على المعايير الحالية
        const filteredWords = this.allWords.filter(word => {
            // تحقق من طول الكلمة
            if (word.length !== wordLength) {
                return false;
            }

            // استخرج حركات كل حرف من الكلمة
            const wordDiacritics = this.getWordLetterDiacritics(word.word);
            
            // تحقق من عدد الحروف
            const normalizedWord = this.normalizeWordForAnalysis(word.word);
            const lettersCount = Array.from(normalizedWord).filter(c => this.isArabicLetter(c)).length;
            if (lettersCount !== wordLength) {
                return false;
            }

            // تحقق من أن كل حرف له حركة مسموح بها
            return wordDiacritics.every((type, index) => {
                const allowed = perLetterDiacritics[index] || [];
                return type === null || allowed.includes(type);
            });
        });

        // تحديث عرض العدد
        if (this.elements.availableWordsCount) {
            this.elements.availableWordsCount.textContent = filteredWords.length;
            
            // تغيير اللون بناءً على العدد
            if (filteredWords.length === 0) {
                this.elements.availableWordsCount.style.color = '#ff6b6b';
            } else if (filteredWords.length < 10) {
                this.elements.availableWordsCount.style.color = '#ffa500';
            } else {
                this.elements.availableWordsCount.style.color = '#ffd700';
            }
        }
    }

    isArabicLetter(char) {
        return /[\u0621-\u064A]/.test(char);
    }

    normalizeWordForAnalysis(word) {
        return (word || '')
            .normalize('NFKC')
            .replace(/[\u0640\u200D]/g, '');
    }

    getDiacriticType(diacritics) {
        const diacriticsSet = new Set(diacritics);
        if (diacriticsSet.has('\u064B') || diacriticsSet.has('\u064C') || diacriticsSet.has('\u064D')) {
            return 'TANWEEN';
        }
        if (diacriticsSet.has('\u0652')) {
            return 'SUKOON';
        }
        if (diacriticsSet.has('\u064E')) {
            return 'FATHA';
        }
        if (diacriticsSet.has('\u0650')) {
            return 'KASRA';
        }
        if (diacriticsSet.has('\u064F')) {
            return 'DAMMA';
        }
        return null;
    }

    getWordLetterDiacritics(word) {
        const letters = [];
        const normalizedWord = this.normalizeWordForAnalysis(word);
        const chars = Array.from(normalizedWord);
        chars.forEach(char => {
            if (this.isArabicLetter(char)) {
                letters.push({ letter: char, diacritics: [] });
            } else if (letters.length > 0) {
                letters[letters.length - 1].diacritics.push(char);
            }
        });

        return letters.map(letter => this.getDiacriticType(letter.diacritics));
    }

    clearData() {
        const confirmMsg = 'تحذير! سيتم حذف جميع البيانات والتقدم المحرز.\n\nهل أنت متأكد من المتابعة؟';
        
        if (confirm(confirmMsg)) {
            // طلب تأكيد إضافي
            const doubleConfirm = prompt('اكتب "نعم" للتأكيد:');
            
            if (doubleConfirm === 'نعم' || doubleConfirm === 'yes') {
                StorageManager.clearAll();
                this.showMessage('تم مسح جميع البيانات! 🗑️');
                
                // إعادة التحميل بعد ثانيتين
                setTimeout(() => {
                    window.location.href = 'index.html';
                }, 2000);
            }
        }
    }

    showMessage(message) {
        // إنشاء عنصر الرسالة
        const messageDiv = document.createElement('div');
        messageDiv.textContent = message;
        messageDiv.style.cssText = `
            position: fixed;
            top: 20px;
            right: 50%;
            transform: translateX(50%);
            background: #4CAF50;
            color: white;
            padding: 15px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.2);
            z-index: 1000;
            font-size: 1.1rem;
            font-weight: bold;
            animation: slideDown 0.3s ease-out;
        `;

        // إضافة للصفحة
        document.body.appendChild(messageDiv);

        // إزالة بعد 3 ثواني
        setTimeout(() => {
            messageDiv.style.animation = 'slideUp 0.3s ease-out';
            setTimeout(() => {
                document.body.removeChild(messageDiv);
            }, 300);
        }, 2500);
    }
}

// تشغيل صفحة الإعدادات عند تحميل الصفحة
document.addEventListener('DOMContentLoaded', () => {
    new SettingsPage();
});

// إضافة أنيميشن CSS
const style = document.createElement('style');
style.textContent = `
    @keyframes slideDown {
        from {
            opacity: 0;
            transform: translateX(50%) translateY(-20px);
        }
        to {
            opacity: 1;
            transform: translateX(50%) translateY(0);
        }
    }
    
    @keyframes slideUp {
        from {
            opacity: 1;
            transform: translateX(50%) translateY(0);
        }
        to {
            opacity: 0;
            transform: translateX(50%) translateY(-20px);
        }
    }
`;
document.head.appendChild(style);
