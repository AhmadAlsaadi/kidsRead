/**
 * التطبيق الرئيسي
 */

class KidsReadApp {
    constructor() {
        this.allWords = [];
        this.currentSession = {
            words: [],
            currentIndex: 0,
            correctCount: 0,
            incorrectCount: 0
        };
        this.settings = StorageManager.loadSettings();
        
        this.init();
    }

    async init() {
        // تحميل الكلمات من JSON
        await this.loadWords();
        
        // تهيئة الواجهة
        this.initUI();
        
        // تطبيق الإعدادات
        this.applySettings();
        
        // ربط الأحداث
        this.bindEvents();
    }

    async loadWords() {
        try {
            // محاولة تحميل من LocalStorage
            let storedWords = StorageManager.loadWords();
            
            if (storedWords.length === 0) {
                // تحميل من JSON
                const response = await fetch('data/words.json');
                const words = await response.json();
                
                // إضافة معلومات التكرار المتباعد لكل كلمة
                this.allWords = words.map(word => ({
                    ...word,
                    reviewCount: 0,
                    lastReviewDate: 0,
                    nextReviewDate: Date.now(),
                    easeFactor: 2.5,
                    interval: 0,
                    difficulty: 'NEW'
                }));
                
                // حفظ في LocalStorage
                StorageManager.saveWords(this.allWords);
            } else {
                this.allWords = storedWords;
            }
        } catch (error) {
            console.error('خطأ في تحميل الكلمات:', error);
            alert('حدث خطأ في تحميل الكلمات. الرجاء تحديث الصفحة.');
        }
    }

    initUI() {
        this.elements = {
            wordDisplay: document.getElementById('wordDisplay'),
            wordContainer: document.getElementById('wordContainer'),
            statsSection: document.getElementById('statsSection'),
            currentWord: document.getElementById('currentWord'),
            totalWords: document.getElementById('totalWords'),
            correctCount: document.getElementById('correctCount'),
            incorrectCount: document.getElementById('incorrectCount'),
            startBtn: document.getElementById('startBtn'),
            easyBtn: document.getElementById('easyBtn'),
            mediumBtn: document.getElementById('mediumBtn'),
            hardBtn: document.getElementById('hardBtn'),
            sessionComplete: document.getElementById('sessionComplete'),
            successRate: document.getElementById('successRate'),
            newSessionBtn: document.getElementById('newSessionBtn'),
            settingsBtn: document.getElementById('settingsBtn')
        };

        // إخفاء أزرار التقييم في البداية
        this.setEvaluationButtonsState(false);
    }

    bindEvents() {
        this.elements.startBtn.addEventListener('click', () => this.startSession());
        this.elements.newSessionBtn.addEventListener('click', () => this.startSession());
        this.elements.easyBtn.addEventListener('click', () => this.evaluateWord('EASY'));
        this.elements.mediumBtn.addEventListener('click', () => this.evaluateWord('MEDIUM'));
        this.elements.hardBtn.addEventListener('click', () => this.evaluateWord('HARD'));
        this.elements.settingsBtn.addEventListener('click', () => {
            window.location.href = 'settings.html';
        });
    }

    applySettings() {
        // تطبيق لون الخلفية
        this.elements.wordContainer.style.backgroundColor = this.settings.backgroundColor;
        
        // تطبيق لون الكلمة
        this.elements.wordDisplay.style.color = this.settings.wordColor;
        
        // تطبيق حجم الخط
        this.elements.wordDisplay.style.fontSize = `${this.settings.fontSize}px`;
        
        // إظهار/إخفاء الإحصائيات
        if (this.settings.showStats) {
            this.elements.statsSection.classList.remove('hidden');
        } else {
            this.elements.statsSection.classList.add('hidden');
        }
    }

    startSession() {
        // إخفاء رسالة نهاية الجلسة
        this.elements.sessionComplete.classList.add('hidden');
        
        // فلترة الكلمات حسب الإعدادات
        const filteredWords = this.allWords.filter(word => {
            return word.length === this.settings.wordLength &&
                   this.settings.selectedDiacritics.includes(word.diacriticType);
        });

        if (filteredWords.length === 0) {
            alert('لا توجد كلمات متاحة بالإعدادات الحالية. الرجاء تغيير الإعدادات.');
            return;
        }

        // الحصول على الكلمات المستحقة للمراجعة
        const dueWords = SpacedRepetitionAlgorithm.getWordsForReview(filteredWords);
        
        // ترتيب حسب الأولوية
        const sortedWords = SpacedRepetitionAlgorithm.sortByPriority(dueWords);
        
        // اختيار عدد الكلمات المطلوبة
        this.currentSession = {
            words: sortedWords.slice(0, this.settings.wordsPerSession),
            currentIndex: 0,
            correctCount: 0,
            incorrectCount: 0
        };

        if (this.currentSession.words.length === 0) {
            alert('رائع! لقد أتممت جميع الكلمات. جرب غداً!');
            return;
        }

        // إظهار أزرار التقييم
        this.setEvaluationButtonsState(true);
        this.elements.startBtn.classList.add('hidden');

        // عرض أول كلمة
        this.showCurrentWord();
        this.updateStats();
    }

    showCurrentWord() {
        if (this.currentSession.currentIndex < this.currentSession.words.length) {
            const word = this.currentSession.words[this.currentSession.currentIndex];
            this.elements.wordDisplay.textContent = word.word;
            
            // تأثير الظهور
            this.elements.wordDisplay.style.animation = 'none';
            setTimeout(() => {
                this.elements.wordDisplay.style.animation = 'fadeIn 0.5s ease-in';
            }, 10);
        }
    }

    evaluateWord(evaluation) {
        const currentWord = this.currentSession.words[this.currentSession.currentIndex];
        
        // تحديث عداد الإجابات
        if (evaluation === 'EASY') {
            this.currentSession.correctCount++;
        } else {
            this.currentSession.incorrectCount++;
        }

        // تحديث الكلمة باستخدام الخوارزمية
        const quality = SpacedRepetitionAlgorithm.evaluationToQuality(evaluation);
        const updatedWord = SpacedRepetitionAlgorithm.calculateNext(currentWord, quality);
        
        // حفظ في قاعدة البيانات
        StorageManager.updateWord(updatedWord.id, updatedWord);
        
        // تحديث في القائمة المحلية
        const index = this.allWords.findIndex(w => w.id === updatedWord.id);
        if (index !== -1) {
            this.allWords[index] = updatedWord;
        }

        // الانتقال للكلمة التالية
        this.currentSession.currentIndex++;
        
        if (this.currentSession.currentIndex < this.currentSession.words.length) {
            this.showCurrentWord();
            this.updateStats();
        } else {
            this.endSession();
        }
    }

    updateStats() {
        this.elements.currentWord.textContent = this.currentSession.currentIndex + 1;
        this.elements.totalWords.textContent = this.currentSession.words.length;
        this.elements.correctCount.textContent = this.currentSession.correctCount;
        this.elements.incorrectCount.textContent = this.currentSession.incorrectCount;
    }

    endSession() {
        // حفظ الجلسة
        const sessionData = {
            correctCount: this.currentSession.correctCount,
            incorrectCount: this.currentSession.incorrectCount,
            totalWords: this.currentSession.words.length,
            wordLength: this.settings.wordLength,
            diacritics: this.settings.selectedDiacritics
        };
        StorageManager.saveSession(sessionData);

        // حساب نسبة النجاح
        const total = this.currentSession.correctCount + this.currentSession.incorrectCount;
        const successRate = total > 0 
            ? Math.round((this.currentSession.correctCount / total) * 100)
            : 0;

        // إظهار رسالة النهاية
        this.elements.successRate.textContent = successRate;
        this.elements.sessionComplete.classList.remove('hidden');
        this.elements.wordDisplay.textContent = '';
        
        // إخفاء أزرار التقييم
        this.setEvaluationButtonsState(false);
    }

    setEvaluationButtonsState(enabled) {
        this.elements.easyBtn.disabled = !enabled;
        this.elements.mediumBtn.disabled = !enabled;
        this.elements.hardBtn.disabled = !enabled;
        
        if (enabled) {
            this.elements.easyBtn.style.display = 'block';
            this.elements.mediumBtn.style.display = 'block';
            this.elements.hardBtn.style.display = 'block';
        } else {
            this.elements.easyBtn.style.display = 'none';
            this.elements.mediumBtn.style.display = 'none';
            this.elements.hardBtn.style.display = 'none';
        }
    }
}

// تشغيل التطبيق عند تحميل الصفحة
document.addEventListener('DOMContentLoaded', () => {
    new KidsReadApp();
});
