package com.autologin.wifi

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.net.NetworkInfo
import android.util.Log
import com.autologin.R
import com.autologin.Debug

class NetworkChangeReceiver: BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    val action = intent.getAction()
    val account = WifiAccount(context)
    val manager = getWifiManager(context)
    Log.d(Debug.TAG, "Receiver: Receive network event")

    if (!account.isLogin) {
      Log.i(Debug.TAG, "Receiver: Not login")
      return
    }

    if (action == WifiManager.NETWORK_STATE_CHANGED_ACTION) {
      val state = getNetworkState(intent)
      Log.i(Debug.TAG, "Receiver: Receive network change event")
      if (state === NetworkInfo.State.CONNECTED) { // Network is connect
        Log.d(Debug.TAG, "Receiver: State is connect")
        val connectingSSID = getSSID(manager)
        if (connectingSSID == "CCU") {
          Log.d(Debug.TAG, "Receiver: Match CCU")
          Log.d(Debug.TAG, "Receiver: Start login service")
          context.startService(Intent(context, WifiLoginService::class.java))
        }
      }
    }
  }

  private fun getWifiManager(context: Context): WifiManager {
    return context.getSystemService(Context.WIFI_SERVICE) as WifiManager
  }

  private fun getNetworkState(intent: Intent): NetworkInfo.State {
    val networkInfo: NetworkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO)
    return networkInfo.getState()
  }

  private fun getSSID(manager: WifiManager): String {
    return manager.getConnectionInfo().getSSID().replace("\"", "")
  }
}
