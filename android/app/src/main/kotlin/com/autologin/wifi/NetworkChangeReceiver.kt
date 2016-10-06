package com.autologin.wifi

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.wifi.WifiManager
import android.net.NetworkInfo
import android.preference.PreferenceManager
import android.util.Log
import com.autologin.R
import com.autologin.Debug
import com.autologin.wifi.WifiLoginService

class NetworkChangeReceiver:BroadcastReceiver() {
  @TargetApi(11)
  override fun onReceive(context:Context, intent:Intent) {
    val action = intent.getAction()
    val account = WifiAccount(context)
    Log.d(Debug.TAG, "Receiver: Receive network event")
    if (!account.isLogin()) {
      Log.i(Debug.TAG, "Receiver: Not login")
      return
    }
    if (action == WifiManager.NETWORK_STATE_CHANGED_ACTION) {
      Log.i(Debug.TAG, "Receiver: Receive network change event")
      val manager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
      val networkInfo : NetworkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO)
      val state = networkInfo.getState()
      if (state === NetworkInfo.State.CONNECTED) { // Network is connect
        Log.d(Debug.TAG, "Receiver: State is connect")
        val connectingSSID = manager.getConnectionInfo().getSSID().replace("\"", "")
        if (connectingSSID == "CCU") {
          Log.d(Debug.TAG, "Receiver: Match CCU")
          Log.d(Debug.TAG, "Receiver: Start login service")
          context.startService(Intent(context, WifiLoginService::class.java))
        }
      }
    }
  }
}
