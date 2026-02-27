#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Execute the vocabulary addition directly
"""

import json

# New 241 common 3-letter Arabic words
new_words = [
    # Family & Body Parts (IDs 215-234)
    {"id": 215, "word": "أَمٌّ", "length": 3, "diacriticType": "FATHA"},  # mother
    {"id": 216, "word": "أَبٌ", "length": 3, "diacriticType": "FATHA"},  # father
    {"id": 217, "word": "أَخٌ", "length": 3, "diacriticType": "FATHA"},  # brother
    {"id": 218, "word": "ابْنٌ", "length": 3, "diacriticType": "FATHA"},  # son
    {"id": 219, "word": "بِنْتٌ", "length": 3, "diacriticType": "KASRA"},  # daughter
    {"id": 220, "word": "عَمٌّ", "length": 3, "diacriticType": "FATHA"},  # uncle (paternal)
    {"id": 221, "word": "عَمَّةٌ", "length": 3, "diacriticType": "FATHA"},  # aunt (paternal)
    {"id": 222, "word": "خَالٌ", "length": 3, "diacriticType": "FATHA"},  # uncle (maternal)
    {"id": 223, "word": "خَالَةٌ", "length": 3, "diacriticType": "FATHA"},  # aunt (maternal)
    {"id": 224, "word": "جَدٌّ", "length": 3, "diacriticType": "FATHA"},  # grandfather
    {"id": 225, "word": "رَأْسٌ", "length": 3, "diacriticType": "FATHA"},  # head
    {"id": 226, "word": "عَيْنٌ", "length": 3, "diacriticType": "FATHA"},  # eye
    {"id": 227, "word": "أُذُنٌ", "length": 3, "diacriticType": "DAMMA"},  # ear
    {"id": 228, "word": "نَفْسٌ", "length": 3, "diacriticType": "FATHA"},  # self/soul
    {"id": 229, "word": "جَسَدٌ", "length": 3, "diacriticType": "FATHA"},  # body
    {"id": 230, "word": "يَدٌ", "length": 3, "diacriticType": "FATHA"},  # hand
    {"id": 231, "word": "رِجْلٌ", "length": 3, "diacriticType": "KASRA"},  # leg
    {"id": 232, "word": "قَلْبٌ", "length": 3, "diacriticType": "FATHA"},  # heart
    {"id": 233, "word": "بَطْنٌ", "length": 3, "diacriticType": "FATHA"},  # stomach
    {"id": 234, "word": "ظَهْرٌ", "length": 3, "diacriticType": "FATHA"},  # back
    
    # Clothes & Accessories (IDs 235-254)
    {"id": 235, "word": "قَمِيصٌ", "length": 3, "diacriticType": "FATHA"},  # shirt
    {"id": 236, "word": "ثَوْبٌ", "length": 3, "diacriticType": "FATHA"},  # dress/garment
    {"id": 237, "word": "جَوْرَبٌ", "length": 3, "diacriticType": "FATHA"},  # sock
    {"id": 238, "word": "حِذَاءٌ", "length": 3, "diacriticType": "KASRA"},  # shoe
    {"id": 239, "word": "قُبَّعَةٌ", "length": 3, "diacriticType": "DAMMA"},  # hat
    {"id": 240, "word": "رِطَاقٌ", "length": 3, "diacriticType": "KASRA"},  # belt
    {"id": 241, "word": "كِيسٌ", "length": 3, "diacriticType": "KASRA"},  # bag
    {"id": 242, "word": "سِتَارَةٌ", "length": 3, "diacriticType": "KASRA"},  # curtain
    {"id": 243, "word": "بِطَاقَةٌ", "length": 3, "diacriticType": "KASRA"},  # card
    {"id": 244, "word": "حَقِيبَةٌ", "length": 3, "diacriticType": "FATHA"},  # suitcase
    {"id": 245, "word": "جَاكِتٌ", "length": 3, "diacriticType": "FATHA"},  # jacket
    {"id": 246, "word": "رِطَاقٌ", "length": 3, "diacriticType": "KASRA"},  # scarf
    {"id": 247, "word": "عِقْدٌ", "length": 3, "diacriticType": "KASRA"},  # necklace
    {"id": 248, "word": "خَاتَمٌ", "length": 3, "diacriticType": "FATHA"},  # ring
    {"id": 249, "word": "سَاعَةٌ", "length": 3, "diacriticType": "FATHA"},  # watch
    {"id": 250, "word": "نَظَّارَةٌ", "length": 3, "diacriticType": "FATHA"},  # glasses
    {"id": 251, "word": "كِفٌّ", "length": 3, "diacriticType": "KASRA"},  # glove
    {"id": 252, "word": "عِمَامَةٌ", "length": 3, "diacriticType": "KASRA"},  # turban
    {"id": 253, "word": "ثِيَابٌ", "length": 3, "diacriticType": "KASRA"},  # clothes
    {"id": 254, "word": "إِزَارٌ", "length": 3, "diacriticType": "KASRA"},  # skirt
    
    # Food & Drinks (IDs 255-274)
    {"id": 255, "word": "خُبْزٌ", "length": 3, "diacriticType": "DAMMA"},  # bread
    {"id": 256, "word": "أَرُزٌ", "length": 3, "diacriticType": "FATHA"},  # rice
    {"id": 257, "word": "لَحْمٌ", "length": 3, "diacriticType": "FATHA"},  # meat
    {"id": 258, "word": "دَجَاجٌ", "length": 3, "diacriticType": "FATHA"},  # chicken
    {"id": 259, "word": "سَمَكٌ", "length": 3, "diacriticType": "FATHA"},  # fish
    {"id": 260, "word": "بَيْضٌ", "length": 3, "diacriticType": "FATHA"},  # eggs
    {"id": 261, "word": "حَلِيبٌ", "length": 3, "diacriticType": "FATHA"},  # milk
    {"id": 262, "word": "جُبْنٌ", "length": 3, "diacriticType": "DAMMA"},  # cheese
    {"id": 263, "word": "زُبْدَةٌ", "length": 3, "diacriticType": "DAMMA"},  # butter
    {"id": 264, "word": "عَسَلٌ", "length": 3, "diacriticType": "FATHA"},  # honey
    {"id": 265, "word": "مَرَقٌ", "length": 3, "diacriticType": "FATHA"},  # broth
    {"id": 266, "word": "مَاءٌ", "length": 3, "diacriticType": "FATHA"},  # water
    {"id": 267, "word": "عَصِيرٌ", "length": 3, "diacriticType": "FATHA"},  # juice
    {"id": 268, "word": "شَايٌ", "length": 3, "diacriticType": "FATHA"},  # tea
    {"id": 269, "word": "قَهْوَةٌ", "length": 3, "diacriticType": "FATHA"},  # coffee
    {"id": 270, "word": "حِنْطَةٌ", "length": 3, "diacriticType": "KASRA"},  # wheat
    {"id": 271, "word": "دَقِيقٌ", "length": 3, "diacriticType": "FATHA"},  # flour
    {"id": 272, "word": "مِلْحٌ", "length": 3, "diacriticType": "KASRA"},  # salt
    {"id": 273, "word": "سُكَّرٌ", "length": 3, "diacriticType": "DAMMA"},  # sugar
    {"id": 274, "word": "زَيْتٌ", "length": 3, "diacriticType": "FATHA"},  # oil
    
    # Fruits & Vegetables (IDs 275-289)
    {"id": 275, "word": "تُفَّاحٌ", "length": 3, "diacriticType": "DAMMA"},  # apple
    {"id": 276, "word": "بُرْتُقَالٌ", "length": 3, "diacriticType": "DAMMA"},  # orange
    {"id": 277, "word": "مَوْزٌ", "length": 3, "diacriticType": "FATHA"},  # banana
    {"id": 278, "word": "عِنَبٌ", "length": 3, "diacriticType": "KASRA"},  # grapes
    {"id": 279, "word": "رُمَّانٌ", "length": 3, "diacriticType": "DAMMA"},  # pomegranate
    {"id": 280, "word": "شَمَّامٌ", "length": 3, "diacriticType": "FATHA"},  # melon
    {"id": 281, "word": "خِيَارٌ", "length": 3, "diacriticType": "KASRA"},  # cucumber
    {"id": 282, "word": "بَاذِنْجَانٌ", "length": 3, "diacriticType": "FATHA"},  # eggplant
    {"id": 283, "word": "جَزَرٌ", "length": 3, "diacriticType": "FATHA"},  # carrot
    {"id": 284, "word": "بَصَلٌ", "length": 3, "diacriticType": "FATHA"},  # onion
    {"id": 285, "word": "ثَوْمٌ", "length": 3, "diacriticType": "FATHA"},  # garlic
    {"id": 286, "word": "طَمَاطِمٌ", "length": 3, "diacriticType": "FATHA"},  # tomato
    {"id": 287, "word": "فِلْفِلٌ", "length": 3, "diacriticType": "KASRA"},  # pepper
    {"id": 288, "word": "مَلِفُوفٌ", "length": 3, "diacriticType": "FATHA"},  # cabbage
    {"id": 289, "word": "سَبَانِخٌ", "length": 3, "diacriticType": "FATHA"},  # spinach
    
    # Animals (IDs 290-316)
    {"id": 290, "word": "كَلْبٌ", "length": 3, "diacriticType": "FATHA"},  # dog
    {"id": 291, "word": "قِطٌّ", "length": 3, "diacriticType": "KASRA"},  # cat
    {"id": 292, "word": "أَسَدٌ", "length": 3, "diacriticType": "FATHA"},  # lion
    {"id": 293, "word": "نِمِرٌ", "length": 3, "diacriticType": "KASRA"},  # tiger
    {"id": 294, "word": "فِيلٌ", "length": 3, "diacriticType": "KASRA"},  # elephant
    {"id": 295, "word": "حِصَانٌ", "length": 3, "diacriticType": "KASRA"},  # horse
    {"id": 296, "word": "حِمَارٌ", "length": 3, "diacriticType": "KASRA"},  # donkey
    {"id": 297, "word": "بَقَرَةٌ", "length": 3, "diacriticType": "FATHA"},  # cow
    {"id": 298, "word": "غَنَمٌ", "length": 3, "diacriticType": "FATHA"},  # sheep
    {"id": 299, "word": "عَنْزٌ", "length": 3, "diacriticType": "FATHA"},  # goat
    {"id": 300, "word": "خِنْزِيرٌ", "length": 3, "diacriticType": "KASRA"},  # pig
    {"id": 301, "word": "طَيْرٌ", "length": 3, "diacriticType": "FATHA"},  # bird
    {"id": 302, "word": "حَمَامٌ", "length": 3, "diacriticType": "FATHA"},  # pigeon
    {"id": 303, "word": "دِيكٌ", "length": 3, "diacriticType": "KASRA"},  # rooster
    {"id": 304, "word": "دَجَاجَةٌ", "length": 3, "diacriticType": "FATHA"},  # hen
    {"id": 305, "word": "بَطَّةٌ", "length": 3, "diacriticType": "FATHA"},  # duck
    {"id": 306, "word": "إِوَزَّةٌ", "length": 3, "diacriticType": "KASRA"},  # goose
    {"id": 307, "word": "نَحْلٌ", "length": 3, "diacriticType": "FATHA"},  # bee
    {"id": 308, "word": "فَرَاشَةٌ", "length": 3, "diacriticType": "FATHA"},  # butterfly
    {"id": 309, "word": "ثُعْبَانٌ", "length": 3, "diacriticType": "DAMMA"},  # snake
    {"id": 310, "word": "ضِفْدَعٌ", "length": 3, "diacriticType": "KASRA"},  # frog
    {"id": 311, "word": "سُلَحْفَاةٌ", "length": 3, "diacriticType": "DAMMA"},  # turtle
    {"id": 312, "word": "سَمَكٌ", "length": 3, "diacriticType": "FATHA"},  # fish
    {"id": 313, "word": "سِنْجَابٌ", "length": 3, "diacriticType": "KASRA"},  # squirrel
    {"id": 314, "word": "أَرْنَبٌ", "length": 3, "diacriticType": "FATHA"},  # rabbit
    {"id": 315, "word": "فَأْرٌ", "length": 3, "diacriticType": "FATHA"},  # mouse
    {"id": 316, "word": "غُرَابٌ", "length": 3, "diacriticType": "DAMMA"},  # crow
    
    # Nature & Places (IDs 317-336)
    {"id": 317, "word": "سَمَاءٌ", "length": 3, "diacriticType": "FATHA"},  # sky
    {"id": 318, "word": "أَرْضٌ", "length": 3, "diacriticType": "FATHA"},  # earth
    {"id": 319, "word": "حَقْلٌ", "length": 3, "diacriticType": "FATHA"},  # field
    {"id": 320, "word": "شَجَرَةٌ", "length": 3, "diacriticType": "FATHA"},  # tree
    {"id": 321, "word": "وَرْدَةٌ", "length": 3, "diacriticType": "FATHA"},  # rose
    {"id": 322, "word": "زَهْرَةٌ", "length": 3, "diacriticType": "FATHA"},  # flower
    {"id": 323, "word": "جِبَلٌ", "length": 3, "diacriticType": "KASRA"},  # mountain
    {"id": 324, "word": "وَادٍ", "length": 3, "diacriticType": "FATHA"},  # valley
    {"id": 325, "word": "نَهْرٌ", "length": 3, "diacriticType": "FATHA"},  # river
    {"id": 326, "word": "بِئْرٌ", "length": 3, "diacriticType": "KASRA"},  # well
    {"id": 327, "word": "بَحْرٌ", "length": 3, "diacriticType": "FATHA"},  # sea
    {"id": 328, "word": "شَاطِئٌ", "length": 3, "diacriticType": "FATHA"},  # beach
    {"id": 329, "word": "رِيحٌ", "length": 3, "diacriticType": "KASRA"},  # wind
    {"id": 330, "word": "مَطَرٌ", "length": 3, "diacriticType": "FATHA"},  # rain
    {"id": 331, "word": "ثَلْجٌ", "length": 3, "diacriticType": "FATHA"},  # snow
    {"id": 332, "word": "جَلِيدٌ", "length": 3, "diacriticType": "FATHA"},  # ice
    {"id": 333, "word": "غَيْمٌ", "length": 3, "diacriticType": "FATHA"},  # cloud
    {"id": 334, "word": "دُخَانٌ", "length": 3, "diacriticType": "DAMMA"},  # smoke
    {"id": 335, "word": "نَارٌ", "length": 3, "diacriticType": "FATHA"},  # fire
    {"id": 336, "word": "ضَوْءٌ", "length": 3, "diacriticType": "FATHA"},  # light
    
    # House & Furniture (IDs 337-356)
    {"id": 337, "word": "بَيْتٌ", "length": 3, "diacriticType": "FATHA"},  # house
    {"id": 338, "word": "غُرْفَةٌ", "length": 3, "diacriticType": "DAMMA"},  # room
    {"id": 339, "word": "سَرِيرٌ", "length": 3, "diacriticType": "FATHA"},  # bed
    {"id": 340, "word": "كُرْسِيٌّ", "length": 3, "diacriticType": "DAMMA"},  # chair
    {"id": 341, "word": "طَاوِلَةٌ", "length": 3, "diacriticType": "FATHA"},  # table
    {"id": 342, "word": "دِرَاجٌ", "length": 3, "diacriticType": "KASRA"},  # stairs
    {"id": 343, "word": "بَابٌ", "length": 3, "diacriticType": "FATHA"},  # door
    {"id": 344, "word": "نَافِذَةٌ", "length": 3, "diacriticType": "FATHA"},  # window
    {"id": 345, "word": "حَائِطٌ", "length": 3, "diacriticType": "FATHA"},  # wall
    {"id": 346, "word": "سَقْفٌ", "length": 3, "diacriticType": "FATHA"},  # ceiling
    {"id": 347, "word": "أَرْضِيَّةٌ", "length": 3, "diacriticType": "FATHA"},  # floor
    {"id": 348, "word": "مِصْبَاحٌ", "length": 3, "diacriticType": "KASRA"},  # lamp
    {"id": 349, "word": "مِرْآةٌ", "length": 3, "diacriticType": "KASRA"},  # mirror
    {"id": 350, "word": "صُورَةٌ", "length": 3, "diacriticType": "DAMMA"},  # picture
    {"id": 351, "word": "سِجَادَةٌ", "length": 3, "diacriticType": "KASRA"},  # carpet
    {"id": 352, "word": "وِسَادَةٌ", "length": 3, "diacriticType": "KASRA"},  # pillow
    {"id": 353, "word": "بِطَانِيَّةٌ", "length": 3, "diacriticType": "KASRA"},  # blanket
    {"id": 354, "word": "رَفٌّ", "length": 3, "diacriticType": "FATHA"},  # shelf
    {"id": 355, "word": "خِزَانَةٌ", "length": 3, "diacriticType": "KASRA"},  # cabinet
    {"id": 356, "word": "دُولَابٌ", "length": 3, "diacriticType": "DAMMA"},  # wardrobe
    
    # Time & Seasons (IDs 357-369)
    {"id": 357, "word": "يَوْمٌ", "length": 3, "diacriticType": "FATHA"},  # day
    {"id": 358, "word": "لَيْلٌ", "length": 3, "diacriticType": "FATHA"},  # night
    {"id": 359, "word": "صَبَاحٌ", "length": 3, "diacriticType": "FATHA"},  # morning
    {"id": 360, "word": "مَسَاءٌ", "length": 3, "diacriticType": "FATHA"},  # evening
    {"id": 361, "word": "سَاعَةٌ", "length": 3, "diacriticType": "FATHA"},  # hour
    {"id": 362, "word": "دَقِيقَةٌ", "length": 3, "diacriticType": "FATHA"},  # minute
    {"id": 363, "word": "ثَانِيَةٌ", "length": 3, "diacriticType": "FATHA"},  # second
    {"id": 364, "word": "أُسْبُوعٌ", "length": 3, "diacriticType": "DAMMA"},  # week
    {"id": 365, "word": "شَهْرٌ", "length": 3, "diacriticType": "FATHA"},  # month
    {"id": 366, "word": "سَنَةٌ", "length": 3, "diacriticType": "FATHA"},  # year
    {"id": 367, "word": "رَبِيعٌ", "length": 3, "diacriticType": "FATHA"},  # spring
    {"id": 368, "word": "صَيْفٌ", "length": 3, "diacriticType": "FATHA"},  # summer
    {"id": 369, "word": "خَرِيفٌ", "length": 3, "diacriticType": "FATHA"},  # autumn
    
    # Colors (IDs 370-380)
    {"id": 370, "word": "أَسْوَدٌ", "length": 3, "diacriticType": "FATHA"},  # black
    {"id": 371, "word": "أَبْيَضٌ", "length": 3, "diacriticType": "FATHA"},  # white
    {"id": 372, "word": "أَحْمَرٌ", "length": 3, "diacriticType": "FATHA"},  # red
    {"id": 373, "word": "أَزْرَقٌ", "length": 3, "diacriticType": "FATHA"},  # blue
    {"id": 374, "word": "أَخْضَرٌ", "length": 3, "diacriticType": "FATHA"},  # green
    {"id": 375, "word": "أَصْفَرٌ", "length": 3, "diacriticType": "FATHA"},  # yellow
    {"id": 376, "word": "بُرْتُقَالِيٌّ", "length": 3, "diacriticType": "DAMMA"},  # orange
    {"id": 377, "word": "بَنَفْسَجِيٌّ", "length": 3, "diacriticType": "FATHA"},  # purple
    {"id": 378, "word": "وَرْدِيٌّ", "length": 3, "diacriticType": "FATHA"},  # pink
    {"id": 379, "word": "بُنِّيٌّ", "length": 3, "diacriticType": "DAMMA"},  # brown
    {"id": 380, "word": "رَمَادِيٌّ", "length": 3, "diacriticType": "FATHA"},  # gray
    
    # Numbers & Quantities (IDs 381-396)
    {"id": 381, "word": "صِفْرٌ", "length": 3, "diacriticType": "KASRA"},  # zero
    {"id": 382, "word": "وَاحِدٌ", "length": 3, "diacriticType": "FATHA"},  # one
    {"id": 383, "word": "اثْنَانِ", "length": 3, "diacriticType": "FATHA"},  # two
    {"id": 384, "word": "ثَلَاثَةٌ", "length": 3, "diacriticType": "FATHA"},  # three
    {"id": 385, "word": "أَرْبَعَةٌ", "length": 3, "diacriticType": "FATHA"},  # four
    {"id": 386, "word": "خَمْسَةٌ", "length": 3, "diacriticType": "FATHA"},  # five
    {"id": 387, "word": "سِتَّةٌ", "length": 3, "diacriticType": "KASRA"},  # six
    {"id": 388, "word": "سَبْعَةٌ", "length": 3, "diacriticType": "FATHA"},  # seven
    {"id": 389, "word": "ثَمَانِيَةٌ", "length": 3, "diacriticType": "FATHA"},  # eight
    {"id": 390, "word": "تِسْعَةٌ", "length": 3, "diacriticType": "KASRA"},  # nine
    {"id": 391, "word": "عَشَرَةٌ", "length": 3, "diacriticType": "FATHA"},  # ten
    {"id": 392, "word": "عِشْرُونَ", "length": 3, "diacriticType": "KASRA"},  # twenty
    {"id": 393, "word": "ثَلَاثُونَ", "length": 3, "diacriticType": "FATHA"},  # thirty
    {"id": 394, "word": "أَرْبَعُونَ", "length": 3, "diacriticType": "FATHA"},  # forty
    {"id": 395, "word": "مِئَةٌ", "length": 3, "diacriticType": "KASRA"},  # hundred
    {"id": 396, "word": "أَلْفٌ", "length": 3, "diacriticType": "FATHA"},  # thousand
    
    # Actions & Verbs (IDs 397-426)
    {"id": 397, "word": "أَكَلَ", "length": 3, "diacriticType": "FATHA"},  # ate
    {"id": 398, "word": "شَرِبَ", "length": 3, "diacriticType": "FATHA"},  # drank
    {"id": 399, "word": "نَامَ", "length": 3, "diacriticType": "FATHA"},  # slept
    {"id": 400, "word": "اِسْتَيْقَظَ", "length": 3, "diacriticType": "FATHA"},  # woke up
    {"id": 401, "word": "ذَهَبَ", "length": 3, "diacriticType": "FATHA"},  # went
    {"id": 402, "word": "جَاءَ", "length": 3, "diacriticType": "FATHA"},  # came
    {"id": 403, "word": "رَكَضَ", "length": 3, "diacriticType": "FATHA"},  # ran
    {"id": 404, "word": "مَشَى", "length": 3, "diacriticType": "FATHA"},  # walked
    {"id": 405, "word": "رَأَى", "length": 3, "diacriticType": "FATHA"},  # saw
    {"id": 406, "word": "سَمِعَ", "length": 3, "diacriticType": "FATHA"},  # heard
    {"id": 407, "word": "قَالَ", "length": 3, "diacriticType": "FATHA"},  # said
    {"id": 408, "word": "سَأَلَ", "length": 3, "diacriticType": "FATHA"},  # asked
    {"id": 409, "word": "أَجَابَ", "length": 3, "diacriticType": "FATHA"},  # answered
    {"id": 410, "word": "كَتَبَ", "length": 3, "diacriticType": "FATHA"},  # wrote
    {"id": 411, "word": "قَرَأَ", "length": 3, "diacriticType": "FATHA"},  # read
    {"id": 412, "word": "حِسَابٌ", "length": 3, "diacriticType": "KASRA"},  # calculated
    {"id": 413, "word": "لَعِبَ", "length": 3, "diacriticType": "FATHA"},  # played
    {"id": 414, "word": "رَسَمَ", "length": 3, "diacriticType": "FATHA"},  # drew
    {"id": 415, "word": "غَنَّى", "length": 3, "diacriticType": "FATHA"},  # sang
    {"id": 416, "word": "رَقَصَ", "length": 3, "diacriticType": "FATHA"},  # danced
    {"id": 417, "word": "نَزَعَ", "length": 3, "diacriticType": "FATHA"},  # removed
    {"id": 418, "word": "فَتَحَ", "length": 3, "diacriticType": "FATHA"},  # opened
    {"id": 419, "word": "أَغْلَقَ", "length": 3, "diacriticType": "FATHA"},  # closed
    {"id": 420, "word": "وَجَدَ", "length": 3, "diacriticType": "FATHA"},  # found
    {"id": 421, "word": "فَقَدَ", "length": 3, "diacriticType": "FATHA"},  # lost
    {"id": 422, "word": "أَعْطَى", "length": 3, "diacriticType": "FATHA"},  # gave
    {"id": 423, "word": "أَخَذَ", "length": 3, "diacriticType": "FATHA"},  # took
    {"id": 424, "word": "تَعَلَّمَ", "length": 3, "diacriticType": "FATHA"},  # learned
    {"id": 425, "word": "عَمِلَ", "length": 3, "diacriticType": "FATHA"},  # worked
    {"id": 426, "word": "سَاعَدَ", "length": 3, "diacriticType": "FATHA"},  # helped
    
    # Adjectives & States (IDs 427-456)
    {"id": 427, "word": "كَبِيرٌ", "length": 3, "diacriticType": "FATHA"},  # big
    {"id": 428, "word": "صَغِيرٌ", "length": 3, "diacriticType": "FATHA"},  # small
    {"id": 429, "word": "طَوِيلٌ", "length": 3, "diacriticType": "FATHA"},  # tall
    {"id": 430, "word": "قَصِيرٌ", "length": 3, "diacriticType": "FATHA"},  # short
    {"id": 431, "word": "سَمِينٌ", "length": 3, "diacriticType": "FATHA"},  # fat
    {"id": 432, "word": "نَحِيفٌ", "length": 3, "diacriticType": "FATHA"},  # thin
    {"id": 433, "word": "جَمِيلٌ", "length": 3, "diacriticType": "FATHA"},  # beautiful
    {"id": 434, "word": "قَبِيحٌ", "length": 3, "diacriticType": "FATHA"},  # ugly
    {"id": 435, "word": "لَطِيفٌ", "length": 3, "diacriticType": "FATHA"},  # nice
    {"id": 436, "word": "وَاسِعٌ", "length": 3, "diacriticType": "FATHA"},  # wide
    {"id": 437, "word": "ضَيِّقٌ", "length": 3, "diacriticType": "FATHA"},  # narrow
    {"id": 438, "word": "عَالٍ", "length": 3, "diacriticType": "FATHA"},  # high
    {"id": 439, "word": "مُنْخَفِضٌ", "length": 3, "diacriticType": "DAMMA"},  # low
    {"id": 440, "word": "سَرِيعٌ", "length": 3, "diacriticType": "FATHA"},  # fast
    {"id": 441, "word": "بَطِيءٌ", "length": 3, "diacriticType": "FATHA"},  # slow
    {"id": 442, "word": "ثَقِيلٌ", "length": 3, "diacriticType": "FATHA"},  # heavy
    {"id": 443, "word": "خَفِيفٌ", "length": 3, "diacriticType": "FATHA"},  # light
    {"id": 444, "word": "حَارٌّ", "length": 3, "diacriticType": "FATHA"},  # hot
    {"id": 445, "word": "بَارِدٌ", "length": 3, "diacriticType": "FATHA"},  # cold
    {"id": 446, "word": "دَافِئٌ", "length": 3, "diacriticType": "FATHA"},  # warm
    {"id": 447, "word": "مُبْتَلٌّ", "length": 3, "diacriticType": "DAMMA"},  # wet
    {"id": 448, "word": "جَافٌّ", "length": 3, "diacriticType": "FATHA"},  # dry
    {"id": 449, "word": "نَظِيفٌ", "length": 3, "diacriticType": "FATHA"},  # clean
    {"id": 450, "word": "وَسِخٌ", "length": 3, "diacriticType": "FATHA"},  # dirty
    {"id": 451, "word": "قَوِيٌّ", "length": 3, "diacriticType": "FATHA"},  # strong
    {"id": 452, "word": "ضَعِيفٌ", "length": 3, "diacriticType": "FATHA"},  # weak
    {"id": 453, "word": "ذَكِيٌّ", "length": 3, "diacriticType": "FATHA"},  # smart
    {"id": 454, "word": "غَبِيٌّ", "length": 3, "diacriticType": "FATHA"},  # dumb
    {"id": 455, "word": "حَزِينٌ", "length": 3, "diacriticType": "FATHA"},  # sad
    {"id": 456, "word": "سَعِيدٌ", "length": 3, "diacriticType": "FATHA"},  # happy
]

# Read existing words
file_path = '/Users/ahmadalsaadi/Documents/gitRepo/kidsRead/docs/data/words.json'

try:
    with open(file_path, 'r', encoding='utf-8') as f:
        words = json.load(f)
    
    print(f"✅ Successfully read existing words file")
    print(f"📊 Original word count: {len(words)}")
    
    # Add new words
    words.extend(new_words)
    
    # Write back
    with open(file_path, 'w', encoding='utf-8') as f:
        json.dump(words, f, ensure_ascii=False, indent=2)
    
    print(f"✅ Successfully added {len(new_words)} new words!")
    print(f"📊 New total word count: {len(words)}")
    print(f"✅ File updated at: {file_path}")
    
except Exception as e:
    print(f"❌ Error: {e}")
    import traceback
    traceback.print_exc()
