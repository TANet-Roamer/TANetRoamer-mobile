#!/bin/bash

./gradlew assembleRelease
cd app/build/outputs/apk/
jarsigner -verbose -sigalg MD5withRSA -digestalg SHA1 -keystore release.keystore -signedjar app-release.apk app-release-unsigned.apk release
adb install -r app-release.apk
