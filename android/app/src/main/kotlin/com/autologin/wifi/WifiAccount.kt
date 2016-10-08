package com.autologin.wifi

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class WifiAccount(context:Context) {
  private val preferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
  private var username: String? = preferences.getString(KEY_USERNAME, null)
  private var password: String? = preferences.getString(KEY_PASSWORD, null)
  public var isLogin: Boolean = username != null && password != null
    private set

  fun setLoginInfo(username: String, password: String) {
    val prefEditor = preferences.edit()
    isLogin = true
    this.username = username
    this.password = password
    prefEditor.putString(KEY_USERNAME, username)
    prefEditor.putString(KEY_PASSWORD, password)
    prefEditor.apply()
  }

  fun clearLogin() {
    val prefEditor = preferences.edit()
    this.isLogin = false
    prefEditor.putString(KEY_USERNAME, null)
    prefEditor.putString(KEY_PASSWORD, null)
    prefEditor.apply()
  }

  fun getUsername() = if (isLogin) username else null

  fun getPassword() = if (isLogin) password else null

  companion object {
    val PREF_NAME = "CCULIFE_WIFI_PREF"
    val KEY_USERNAME = "wifi_username"
    val KEY_PASSWORD = "wifi_password"
  }
}
