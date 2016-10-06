package com.autologin.wifi

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.autologin.Debug

class WifiLoginService: IntentService("WifiLoginService") {
  override protected fun onHandleIntent(intent: Intent) {
    val account = WifiAccount(this)
    if (!account.isLogin) {
      Log.i(Debug.TAG, "Service: Not login")
      return
    }
    val login = LoginWifi(this, account.getUsername(), account.getPassword())
    Log.i(Debug.TAG, "Service: Start login")
    login.login()
  }
}
