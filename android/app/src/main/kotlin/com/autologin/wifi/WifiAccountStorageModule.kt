package com.autologin.wifi

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.Promise

class WifiAccountStorageModule(reactContext: ReactApplicationContext): ReactContextBaseJavaModule(reactContext) {
  internal val account:WifiAccount = WifiAccount(reactContext)

  override fun getName() = "WifiAccountStorage"

  @ReactMethod
  fun setLoginInfo(username: String, password: String, promise: Promise) {
    account.setLoginInfo(username, password)
    promise.resolve(null)
  }

  @ReactMethod
  fun clearLoginInfo(promise: Promise) {
    account.clearLogin()
    promise.resolve(null)
  }

  @ReactMethod
  fun getLoginInfo(promise: Promise) {
    val map = Arguments.createMap()
    map.putString("username", account.getUsername())
    map.putString("password", account.getPassword())
    promise.resolve(map)
  }
}
