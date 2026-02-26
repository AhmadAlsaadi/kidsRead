#!/bin/zsh

# 🎯 AVD Setup for Intel Mac (x86_64)

set -euo pipefail

SDK_ROOT="$HOME/Library/Android/sdk"
SDKMANAGER="$SDK_ROOT/cmdline-tools/latest/bin/sdkmanager"
AVDMANAGER="$SDK_ROOT/cmdline-tools/latest/bin/avdmanager"

printf "📱 Creating compatible AVD for Intel Mac...\n\n"

# Check if SDK tools exist
if [ ! -f "$SDKMANAGER" ]; then
  printf "❌ SDK Manager not found at: %s\n" "$SDKMANAGER"
  printf "💡 Please run install_android_sdk.sh first\n"
  exit 1
fi

export ANDROID_SDK_ROOT="$SDK_ROOT"

printf "1️⃣  Installing x86_64 system image (API 30)...\n"
"$SDKMANAGER" --sdk_root="$SDK_ROOT" "system-images;android-30;google_apis;x86_64"

printf "\n2️⃣  Accepting licenses...\n"
yes | "$SDKMANAGER" --licenses --sdk_root="$SDK_ROOT" 2>/dev/null || true

printf "\n3️⃣  Creating AVD 'KidsRead_Emulator'...\n"
echo "no" | "$AVDMANAGER" create avd \
  --name "KidsRead_Emulator" \
  --package "system-images;android-30;google_apis;x86_64" \
  --device "pixel_4" \
  --force

printf "\n✅ AVD created successfully!\n\n"

printf "📋 To launch the emulator:\n"
printf "   %s/emulator/emulator -avd KidsRead_Emulator\n\n" "$SDK_ROOT"

printf "📋 Or in Android Studio:\n"
printf "   Tools → Device Manager → KidsRead_Emulator → ▶️ Play\n\n"

printf "🎯 AVD Details:\n"
printf "   • Name: KidsRead_Emulator\n"
printf "   • Device: Pixel 4\n"
printf "   • API Level: 30 (Android 11)\n"
printf "   • ABI: x86_64 (Intel compatible)\n\n"
