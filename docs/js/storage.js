/**
 * إدارة التخزين المحلي (LocalStorage)
 */

class StorageManager {
    static KEYS = {
        WORDS: 'kidsread_words',
        SETTINGS: 'kidsread_settings',
        SESSIONS: 'kidsread_sessions'
    };

    /**
     * حفظ البيانات
     */
    static save(key, data) {
        try {
            localStorage.setItem(key, JSON.stringify(data));
            return true;
        } catch (error) {
            console.error('خطأ في حفظ البيانات:', error);
            return false;
        }
    }

    /**
     * قراءة البيانات
     */
    static load(key, defaultValue = null) {
        try {
            const data = localStorage.getItem(key);
            return data ? JSON.parse(data) : defaultValue;
        } catch (error) {
            console.error('خطأ في قراءة البيانات:', error);
            return defaultValue;
        }
    }

    /**
     * حذف البيانات
     */
    static remove(key) {
        try {
            localStorage.removeItem(key);
            return true;
        } catch (error) {
            console.error('خطأ في حذف البيانات:', error);
            return false;
        }
    }

    /**
     * مسح جميع البيانات
     */
    static clearAll() {
        try {
            Object.values(this.KEYS).forEach(key => {
                localStorage.removeItem(key);
            });
            return true;
        } catch (error) {
            console.error('خطأ في مسح البيانات:', error);
            return false;
        }
    }

    // ====== إدارة الكلمات ======

    static saveWords(words) {
        return this.save(this.KEYS.WORDS, words);
    }

    static loadWords() {
        return this.load(this.KEYS.WORDS, []);
    }

    static updateWord(wordId, updates) {
        const words = this.loadWords();
        const index = words.findIndex(w => w.id === wordId);
        if (index !== -1) {
            words[index] = { ...words[index], ...updates };
            this.saveWords(words);
            return words[index];
        }
        return null;
    }

    // ====== إدارة الإعدادات ======

    static getDefaultSettings() {
        const allDiacritics = ['FATHA', 'KASRA', 'DAMMA', 'SUKOON', 'TANWEEN'];
        return {
            wordLength: 3,
            perLetterDiacritics: [
                [...allDiacritics],
                [...allDiacritics],
                [...allDiacritics],
                [...allDiacritics],
                [...allDiacritics]
            ],
            backgroundColor: '#FFFFFF',
            wordColor: '#000000',
            fontSize: 72,
            fontFamily: "'Arial', sans-serif",
            showStats: true
        };
    }

    static saveSettings(settings) {
        return this.save(this.KEYS.SETTINGS, settings);
    }

    static loadSettings() {
        const defaults = this.getDefaultSettings();
        const loaded = this.load(this.KEYS.SETTINGS, defaults);

        if (!loaded.perLetterDiacritics || !Array.isArray(loaded.perLetterDiacritics)) {
            // إذا لم تكن البيانات صحيحة، استخدم الإعدادات الافتراضية
            return defaults;
        }

        return {
            ...defaults,
            ...loaded
        };
    }

    // إعادة تعيين جميع البيانات
    static resetAllData() {
        this.clearAll();
        return true;
    }

    static resetSettings() {
        return this.saveSettings(this.getDefaultSettings());
    }

    // ====== إدارة الجلسات ======

    static saveSession(session) {
        const sessions = this.loadSessions();
        sessions.push({
            ...session,
            timestamp: Date.now()
        });
        // الحفاظ على آخر 50 جلسة فقط
        if (sessions.length > 50) {
            sessions.shift();
        }
        return this.save(this.KEYS.SESSIONS, sessions);
    }

    static loadSessions() {
        return this.load(this.KEYS.SESSIONS, []);
    }

    static getLastSession() {
        const sessions = this.loadSessions();
        return sessions.length > 0 ? sessions[sessions.length - 1] : null;
    }

    // ====== إحصائيات ======

    static getStatistics() {
        const sessions = this.loadSessions();
        const words = this.loadWords();

        const totalSessions = sessions.length;
        const totalWords = words.length;
        const reviewedWords = words.filter(w => w.reviewCount > 0).length;
        const masteredWords = words.filter(w => w.reviewCount >= 5 && w.easeFactor >= 2.5).length;

        let totalCorrect = 0;
        let totalIncorrect = 0;
        sessions.forEach(session => {
            totalCorrect += session.correctCount || 0;
            totalIncorrect += session.incorrectCount || 0;
        });

        const successRate = totalCorrect + totalIncorrect > 0
            ? Math.round((totalCorrect / (totalCorrect + totalIncorrect)) * 100)
            : 0;

        return {
            totalSessions,
            totalWords,
            reviewedWords,
            masteredWords,
            totalCorrect,
            totalIncorrect,
            successRate
        };
    }
}

// تصدير للاستخدام
if (typeof module !== 'undefined' && module.exports) {
    module.exports = StorageManager;
}
