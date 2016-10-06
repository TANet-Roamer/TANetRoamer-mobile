package com.autologin.wifi;

import javax.annotation.Nullable;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class WifiAccount {
  private boolean login;
  @Nullable private String username, password;
  private SharedPreferences preferences;

  public final static String PREF_NAME = "CCULIFE_WIFI_PREF",
                             KEY_USERNAME = "wifi_username",
                             KEY_PASSWORD = "wifi_password";

  public WifiAccount(Context context) {
    preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    username = preferences.getString(KEY_USERNAME, null);
    password = preferences.getString(KEY_PASSWORD, null);
    login = username != null && password != null;
  }

  public boolean isLogin() {
    return login;
  }

  public void setLoginInfo(String username, String password) {
    Editor prefEditor = preferences.edit();
    login = true;
    username = username;
    password = password;
    prefEditor.putString(KEY_USERNAME, username);
    prefEditor.putString(KEY_PASSWORD, password);
    prefEditor.apply();
  }

  public void clearLogin() {
    Editor prefEditor = preferences.edit();
    login = false;
    prefEditor.putString(KEY_USERNAME, null);
    prefEditor.putString(KEY_PASSWORD, null);
    prefEditor.apply();
  }


  @Nullable
  public String getUsername() {
    return login ? username : null;
  }

  @Nullable
  public String getPassword() {
    return login ? password : null;
  }
}
