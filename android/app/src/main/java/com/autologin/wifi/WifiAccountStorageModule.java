package com.autologin.wifi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Promise;

class WifiAccountStorageModule extends ReactContextBaseJavaModule {
  public WifiAccountStorageModule(ReactApplicationContext reactContext) {
    super(reactContext);
    account = new WifiAccount(reactContext);
  }

  @Override
  public String getName() {
    return "WifiAccountStorage";
  }

  @ReactMethod
  public void setLoginInfo(String username, String password, Promise promise) {
    account.setLoginInfo(username, password);
    promise.resolve(null);
  }

  @ReactMethod
  public void clearLoginInfo(Promise promise) {
    account.clearLogin();
    promise.resolve(null);
  }

  @ReactMethod
  public void getLoginInfo(Promise promise) {
    WritableMap map = Arguments.createMap();
    map.putString("username", account.getUsername());
    map.putString("password", account.getPassword());
    promise.resolve(map);
  }

  private WifiAccount account;
}
