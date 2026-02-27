/**
 * صفحة الإعدادات
 */

class SettingsPage {
    constructor() {
        this.settings = StorageManager.loadSettings();
        this.diacriticTypes = ['FATHA', 'KASRA', 'DAMMA', 'SUKOON', 'TANWEEN'];
        this.init();
    }

    init() {
        this.initElements();
        this.loadSettings();
        this.bindEvents();
    }

    initElements() {
        this.elements = {
            wordLength: document.getElementById('wordLength'),
            bgColor: document.getElementById('bgColor'),
            wordColor: document.getElementById('wordColor'),
            fontSize: document.getElementById('fontSize'),
            fontSizeValue: document.getElementById('fontSizeValue'),
            showStats: document.getElementById('showStats'),
            saveBtn: document.getElementById('saveBtn'),
            resetBtn: document.getElementById('resetBtn'),
            clearDataBtn: document.getElementById('clearDataBtn'),
            backBtn: document.getElementById('backBtn'),
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
        this.elements.showStats.checked = this.settings.showStats;

        this.applyPerLetterDiacriticsToUI();
        this.updateLetterGroupsVisibility();
    }

    bindEvents() {
        // تحديث قيمة حجم الخط
        this.elements.fontSize.addEventListener('input', (e) => {
            this.elements.fontSizeValue.textContent = `${e.target.value}px`;
        });

        this.elements.wordLength.addEventListener('change', () => {
            this.updateLetterGroupsVisibility();
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
