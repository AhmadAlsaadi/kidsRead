/**
 * خوارزمية التكرار المتباعد (Spaced Repetition Algorithm)
 * مبنية على خوارزمية SM-2
 */

class SpacedRepetitionAlgorithm {
    /**
     * حساب الفترة الزمنية التالية للمراجعة
     * @param {Object} word - كائن الكلمة
     * @param {number} quality - جودة الإجابة (0-5)
     * @returns {Object} معلومات محدثة للكلمة
     */
    static calculateNext(word, quality) {
        let easeFactor = word.easeFactor || 2.5;
        let interval = word.interval || 0;
        let reviewCount = word.reviewCount || 0;

        // تحديث معامل السهولة
        easeFactor = Math.max(1.3, easeFactor + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02)));

        // حساب الفترة التالية
        if (quality < 3) {
            // إعادة البداية إذا كانت الإجابة صعبة
            interval = 1;
            reviewCount = 0;
        } else {
            reviewCount++;
            if (reviewCount === 1) {
                interval = 1;
            } else if (reviewCount === 2) {
                interval = 6;
            } else {
                interval = Math.round(interval * easeFactor);
            }
        }

        // حساب وقت المراجعة القادمة (بالدقائق)
        const nextReviewDate = Date.now() + (interval * 24 * 60 * 60 * 1000);

        // تحديد صعوبة الكلمة
        let difficulty;
        if (easeFactor >= 2.5) difficulty = 'EASY';
        else if (easeFactor >= 2.0) difficulty = 'MEDIUM';
        else difficulty = 'HARD';

        return {
            ...word,
            easeFactor,
            interval,
            reviewCount,
            lastReviewDate: Date.now(),
            nextReviewDate,
            difficulty
        };
    }

    /**
     * تحويل تقييم المستخدم إلى قيمة رقمية
     * @param {string} evaluation - التقييم (EASY, MEDIUM, HARD)
     * @returns {number} القيمة الرقمية
     */
    static evaluationToQuality(evaluation) {
        switch (evaluation) {
            case 'EASY': return 5;
            case 'MEDIUM': return 3;
            case 'HARD': return 0;
            default: return 3;
        }
    }

    /**
     * فلترة الكلمات المستحقة للمراجعة
     * @param {Array} words - قائمة الكلمات
     * @param {number} currentTime - الوقت الحالي
     * @returns {Array} الكلمات المستحقة للمراجعة
     */
    static getWordsForReview(words, currentTime = Date.now()) {
        return words.filter(word => {
            const nextReview = word.nextReviewDate || 0;
            return nextReview <= currentTime;
        });
    }

    /**
     * ترتيب الكلمات حسب الأولوية
     * @param {Array} words - قائمة الكلمات
     * @returns {Array} الكلمات مرتبة
     */
    static sortByPriority(words) {
        return words.sort((a, b) => {
            // الكلمات الجديدة أولاً
            if (!a.reviewCount && b.reviewCount) return -1;
            if (a.reviewCount && !b.reviewCount) return 1;

            // ثم حسب وقت المراجعة
            return (a.nextReviewDate || 0) - (b.nextReviewDate || 0);
        });
    }
}

// تصدير للاستخدام
if (typeof module !== 'undefined' && module.exports) {
    module.exports = SpacedRepetitionAlgorithm;
}
