#!/bin/zsh

set -euo pipefail

SDK_ROOT="$HOME/Library/Android/sdk"
CMDLINE_TOOLS_URL="https://dl.google.com/android/repository/commandlinetools-mac-11076708_latest.zip"
CMDLINE_TOOLS_ZIP="$HOME/Downloads/commandlinetools-mac_latest.zip"

printf "🚀 Android SDK install (macOS)\n\n"

mkdir -p "$SDK_ROOT"

printf "⬇️  Downloading command-line tools...\n"
curl -L "$CMDLINE_TOOLS_URL" -o "$CMDLINE_TOOLS_ZIP"

printf "📦 Extracting...\n"
rm -rf "$SDK_ROOT/cmdline-tools"
mkdir -p "$SDK_ROOT/cmdline-tools"
unzip -q "$CMDLINE_TOOLS_ZIP" -d "$SDK_ROOT/cmdline-tools"

if [ -d "$SDK_ROOT/cmdline-tools/cmdline-tools" ]; then
  mv "$SDK_ROOT/cmdline-tools/cmdline-tools" "$SDK_ROOT/cmdline-tools/latest"
fi

printf "✅ Command-line tools installed\n\n"

export ANDROID_SDK_ROOT="$SDK_ROOT"
export PATH="$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools:$PATH"

printf "📦 Installing SDK packages...\n"
"$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager" --sdk_root="$ANDROID_SDK_ROOT" \
  "platform-tools" \
  "platforms;android-34" \
  "build-tools;34.0.0" \
  "emulator"

printf "🧾 Accepting licenses...\n"
yes | "$ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager" --licenses --sdk_root="$ANDROID_SDK_ROOT"

printf "\n✅ SDK installed at: %s\n" "$ANDROID_SDK_ROOT"

ZSHRC="$HOME/.zshrc"
if ! grep -q "ANDROID_SDK_ROOT" "$ZSHRC" 2>/dev/null; then
  printf "\n# Android SDK\nexport ANDROID_SDK_ROOT=\"$ANDROID_SDK_ROOT\"\nexport PATH=\"$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools:\$PATH\"\n" >> "$ZSHRC"
  printf "✅ Added ANDROID_SDK_ROOT to %s\n" "$ZSHRC"
else
  printf "ℹ️  ANDROID_SDK_ROOT already in %s\n" "$ZSHRC"
fi

printf "\n📍 In Android Studio, set SDK Location to:\n%s\n" "$ANDROID_SDK_ROOT"
