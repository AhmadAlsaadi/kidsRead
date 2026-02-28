#!/bin/bash

# Script to commit and push all changes to GitHub

cd /Users/ahmadalsaadi/Documents/gitRepo/kidsRead

echo "📊 Checking git status..."
git status

echo ""
echo "📝 Adding all changes..."
git add -A

echo ""
echo "💾 Committing changes..."
git commit -m "🎓 Enhanced Settings with Available Words Counter

Features:
- Added real-time word availability counter in settings page
- Users can see exact number of words matching their filter criteria
- Counter updates instantly when changing word length or diacritics
- Color coding: Red (<1), Orange (<10), Gold (>10) available words

Improvements:
- Fixed word filtering to accept words without specific diacritics
- Enhanced data loading to intelligently merge updates
- Added getWordLetterDiacritics() and getDiacriticType() utilities
- Improved settings page with visual feedback

Technical Changes:
- docs/settings.html: Added available words display element
- docs/js/settings.js: Added updateAvailableWordsCount() method
- docs/css/style.css: Added styling for words counter
- docs/js/app.js: Fixed word filtering logic
- docs/data/words.json: Contains 455 total words (214 original + 241 new)

Statistics:
- Total words: 455
- 3-letter words: 250+
- Languages: Arabic
- Categories: 12+ (family, food, animals, nature, etc.)
"

echo ""
echo "🚀 Pushing to GitHub..."
git push origin main

echo ""
echo "✅ Done! Changes pushed to GitHub."
