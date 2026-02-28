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
            // تحميل من JSON (دائماً لضمان الحصول على أحدث البيانات)
            const response = await fetch('data/words.json');
            const jsonWords = await response.json();
            
            // محاولة تحميل من LocalStorage
            let storedWords = StorageManager.loadWords();
            
            // تحقق من نسخة البيانات بناءً على آخر ID
            const storedLastId = storedWords.length > 0 ? Math.max(...storedWords.map(w => w.id)) : 0;
            const jsonLastId = jsonWords.length > 0 ? Math.max(...jsonWords.map(w => w.id)) : 0;
            
            // إذا كانت البيانات المخزنة حديثة (نفس الـ IDs)، احتفظ بمعلومات المراجعة
            if (storedWords.length > 0 && storedLastId === jsonLastId) {
                // البيانات محدثة - احتفظ بمعلومات التقدم
                const wordMap = new Map(storedWords.map(w => [w.id, w]));
                this.allWords = jsonWords.map(jsonWord => {
                    const storedWord = wordMap.get(jsonWord.id);
                    if (storedWord) {
                        return {
                            ...jsonWord,
                            reviewCount: storedWord.reviewCount || 0,
                            lastReviewDate: storedWord.lastReviewDate || 0,
                            nextReviewDate: storedWord.nextReviewDate || Date.now(),
                            easeFactor: storedWord.easeFactor || 2.5,
                            interval: storedWord.interval || 0,
                            difficulty: storedWord.difficulty || 'NEW'
                        };
                    }
                    return {
                        ...jsonWord,
                        reviewCount: 0,
                        lastReviewDate: 0,
                        nextReviewDate: Date.now(),
                        easeFactor: 2.5,
                        interval: 0,
                        difficulty: 'NEW'
                    };
                });
            } else {
                // البيانات قديمة أو لا توجد - حمل من JSON وأعد تعيين جميع الكلمات
                console.log(`تحديث البيانات: من ${storedLastId} إلى ${jsonLastId}`);
                this.allWords = jsonWords.map(word => ({
                    ...word,
                    reviewCount: 0,
                    lastReviewDate: 0,
                    nextReviewDate: Date.now(),
                    easeFactor: 2.5,
                    interval: 0,
                    difficulty: 'NEW'
                }));
            }
            
            // حفظ في LocalStorage
            StorageManager.saveWords(this.allWords);
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
        
        // تطبيق نوع الخط
        if (this.settings.fontFamily) {
            this.elements.wordDisplay.style.fontFamily = this.settings.fontFamily;
        }
        
        // إظهار/إخفاء الإحصائيات
        if (this.settings.showStats) {
            this.elements.statsSection.classList.remove('hidden');
        } else {
            this.elements.statsSection.classList.add('hidden');
        }
    }

    getPerLetterDiacritics() {
        if (Array.isArray(this.settings.perLetterDiacritics)) {
            return this.settings.perLetterDiacritics;
        }

        if (Array.isArray(this.settings.selectedDiacritics) && this.settings.selectedDiacritics.length > 0) {
            const fallback = this.settings.selectedDiacritics;
            return [
                [...fallback],
                [...fallback],
                [...fallback],
                [...fallback],
                [...fallback]
            ];
        }

        return [
            ['FATHA', 'KASRA', 'DAMMA', 'SUKOON', 'TANWEEN'],
            ['FATHA', 'KASRA', 'DAMMA', 'SUKOON', 'TANWEEN'],
            ['FATHA', 'KASRA', 'DAMMA', 'SUKOON', 'TANWEEN'],
            ['FATHA', 'KASRA', 'DAMMA', 'SUKOON', 'TANWEEN'],
            ['FATHA', 'KASRA', 'DAMMA', 'SUKOON', 'TANWEEN']
        ];
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

    /**
     * خلط الكلمات عشوائياً (Fisher-Yates Shuffle)
     */
    shuffleArray(array) {
        const shuffled = [...array];
        for (let i = shuffled.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
        }
        return shuffled;
    }

    startSession() {
        // إخفاء رسالة نهاية الجلسة
        this.elements.sessionComplete.classList.add('hidden');
        
        // فلترة الكلمات حسب الإعدادات
        const perLetterDiacritics = this.getPerLetterDiacritics();
        const filteredWords = this.allWords.filter(word => {
            if (word.length !== this.settings.wordLength) {
                return false;
            }

            const wordDiacritics = this.getWordLetterDiacritics(word.word);

            // تحقق من أن عدد الحروف يطابق (تجاهل الحروف بدون حركات إذا كانت موجودة)
            const normalizedWord = this.normalizeWordForAnalysis(word.word);
            const lettersCount = Array.from(normalizedWord).filter(c => this.isArabicLetter(c)).length;
            if (lettersCount !== this.settings.wordLength) {
                return false;
            }

            // للكلمات التي تحتوي على حروف بدون حركات، قم بالفلترة فقط على الحروف التي لها حركات
            return wordDiacritics.every((type, index) => {
                const allowed = perLetterDiacritics[index] || [];
                // إذا لم تكن هناك حركة (null)، اعتبرها صحيحة (حرف بدون حركة مسموح)
                return type === null || allowed.includes(type);
            });
        });

        if (filteredWords.length === 0) {
            alert('لا توجد كلمات متاحة بالإعدادات الحالية. الرجاء تغيير الإعدادات.');
            return;
        }

        // الحصول على الكلمات المستحقة للمراجعة
        const dueWords = SpacedRepetitionAlgorithm.getWordsForReview(filteredWords);
        
        // ترتيب حسب الأولوية
        const sortedWords = SpacedRepetitionAlgorithm.sortByPriority(dueWords);
        
        // خلط الكلمات عشوائياً
        const shuffledWords = this.shuffleArray(sortedWords);
        
        // اختيار عدد الكلمات المطلوبة
        this.currentSession = {
            words: shuffledWords,
            currentIndex: 0,
            correctCount: 0,
            incorrectCount: 0
        };

        // إذا لم توجد كلمات مستحقة للمراجعة، استخدم جميع الكلمات المفلترة للتدريب المستمر
        if (this.currentSession.words.length === 0) {
            console.log('إعادة تشغيل التدريب مع جميع الكلمات المتاحة للتدريب المستمر...');
            const continuousWords = this.shuffleArray(filteredWords);
            this.currentSession.words = continuousWords;
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
            this.elements.wordDisplay.textContent = this.formatWordForDisplay(word);
            
            // تأثير الظهور
            this.elements.wordDisplay.style.animation = 'none';
            setTimeout(() => {
                this.elements.wordDisplay.style.animation = 'fadeIn 0.5s ease-in';
            }, 10);
        }
    }

    formatWordForDisplay(wordObj) {
        const rawWord = wordObj.word || '';

        if (wordObj.length !== 1) {
            return rawWord;
        }

        const normalized = this.normalizeWordForAnalysis(rawWord);
        const letters = Array.from(normalized).filter(char => this.isArabicLetter(char));

        if (letters.length !== 1 || letters[0] !== 'ه') {
            return rawWord;
        }

        const marks = Array.from(normalized).filter(char => !this.isArabicLetter(char));
        return `ﻫ${marks.join('')}ـ`;
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
            diacritics: this.getPerLetterDiacritics()
        };
        StorageManager.saveSession(sessionData);

        // حساب نسبة النجاح
        const total = this.currentSession.correctCount + this.currentSession.incorrectCount;
        const successRate = total > 0 
            ? Math.round((this.currentSession.correctCount / total) * 100)
            : 0;

        // إظهار رسالة النهاية مع إحصائيات مفصلة
        this.elements.successRate.textContent = successRate;
        
        // تحديث رسالة النهاية بشكل ديناميكي
        const completeDiv = this.elements.sessionComplete;
        completeDiv.innerHTML = `
            <h2>🎉 ممتاز!</h2>
            <p>لقد أكملت الجلسة بنجاح!</p>
            <div class="final-stats">
                <div class="stat-row">
                    <span>✅ متقن:</span>
                    <strong>${this.currentSession.correctCount}</strong>
                </div>
                <div class="stat-row">
                    <span>❌ يحتاج تدريب:</span>
                    <strong>${this.currentSession.incorrectCount}</strong>
                </div>
                <div class="stat-row">
                    <span>📊 نسبة النجاح:</span>
                    <strong>${successRate}%</strong>
                </div>
            </div>
            <p class="completion-message">يمكنك بدء جلسة جديدة متى شئت! 💪</p>
        `;
        
        completeDiv.classList.remove('hidden');
        this.elements.wordDisplay.textContent = '';
        
        // إظهار زر البدء لجلسة جديدة
        this.elements.startBtn.classList.remove('hidden');
        this.elements.startBtn.textContent = 'ابدأ جلسة جديدة 🔄';
        
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
