#!/usr/bin/env python3
import sys
import os

# Change to the script directory
os.chdir('/Users/ahmadalsaadi/Documents/gitRepo/kidsRead')

# Import and run the add_vocabulary script
sys.path.insert(0, '/Users/ahmadalsaadi/Documents/gitRepo/kidsRead')
from add_vocabulary import add_words

# Execute the function
result = add_words()
print(f"Script execution result: {result}")
