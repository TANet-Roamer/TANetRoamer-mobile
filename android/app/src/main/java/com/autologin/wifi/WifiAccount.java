package com.autologin.wifi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

class WifiAccount {
  public WifiAccount(Context context) {
    preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    username = preferences.getString(KEY_USERNAME, null);
    password = preferences.getString(KEY_PASSWORD, null);
    _isLogin = username != null && password != null;
  }


  public void setLoginInfo(String username, String password) {
    Editor prefEditor = preferences.edit();
    _isLogin = true;
    this.username = username;
    this.password = password;
    prefEditor.putString(KEY_USERNAME, username);
    prefEditor.putString(KEY_PASSWORD, password);
    prefEditor.apply();
  }

  public void clearLogin() {
    Editor prefEditor = preferences.edit();
    _isLogin = false;
    prefEditor.putString(KEY_USERNAME, null);
    prefEditor.putString(KEY_PASSWORD, null);
    prefEditor.apply();
  }

  public String getUsername() {
    return _isLogin ? username : null;
  }

  public String getPassword() {
    return _isLogin ? password : null;
  }

  public boolean isLogin() {
    return _isLogin;
  }

  private final static String PREF_NAME = "CCULIFE_WIFI_PREF";
  private final static String KEY_USERNAME = "wifi_username";
  private final static String KEY_PASSWORD = "wifi_password";

  private SharedPreferences preferences;
  private String username;
  private String password;
  private boolean _isLogin;
}
