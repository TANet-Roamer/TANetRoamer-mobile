package com.autologin.wifi
import java.util.Random
import java.io.IOException
import java.net.URL
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.Connection
import org.jsoup.Connection.Response
import org.jsoup.nodes.Document
import org.jsoup.parser.Parser
import org.jsoup.select.Elements
import com.autologin.R
import com.autologin.Debug
/**
 * @param username The account for login
 * @param password The password for login
 */
class LoginWifi(private val context: Context, private val username: String?, private val password: String?) {
  /**
  * @return Is login success or not need to login
  */
  fun login(): Boolean {
    if (username == null || password == null) {
      Log.wtf(Debug.TAG, "Login: Username or password is null?!")
      return false
    }
    Log.i(Debug.TAG, "Login: Start LoginTask")
    LoginTask().execute(context)
    return true
  }

  inner class LoginTask: AsyncTask<Context, Void, Boolean>() {
    override protected fun doInBackground(vararg contexts: Context):Boolean {
      Log.i(Debug.TAG, "LoginTask: Start")
      try {
        val idx = Random().nextInt(GOOGLE_IP.size)
        Log.d(Debug.TAG, "LoginTask: Use ip: " + GOOGLE_IP[idx])
        val response = Jsoup
          .connect("http://" + GOOGLE_IP[idx] + "/generate_204")
          .followRedirects(false) // Don't follow, sometime will response 200
          .execute()
        if (response.statusCode() !== 204) { // Maybe we need to login
          Log.i(Debug.TAG, "LoginTask: Need login")
          val url = response.header("location") // Get redirect url
          Log.d(Debug.TAG, "LoginTask: URL: " + url)
          if (url != null && url.startsWith("http://140.123.1.53")) { // It is ccu wireless login
            val login_page = Jsoup
              .connect(WIFI_LOGIN_URL)
              .data("buttonClicked", "4")
              .data("redirect_url", "http://${GOOGLE_IP[idx]}/generate_204")
              .data("err_flag", "0")
              .data("username", username)
              .data("password", password)
              .cookies(response.cookies())
              .method(Connection.Method.POST)
              .followRedirects(true)
              .execute()
            Log.i(Debug.TAG, "LoginTask: Send request")
            val page = Jsoup.parse(login_page.body())
            val body = page.body().text()
            if (body.contains("You can now use all our regular network services")) { // If success, body will contain
              Log.i(Debug.TAG, "LoginTask: Response logined")
              return true
            }
            Log.i(Debug.TAG, "LoginTask: Fail to login")
            return false
          } else {
            Log.i(Debug.TAG, "LoginTask: Not ccu")
            return false
          }
        }
      } catch (e: IOException) {
        Log.w(Debug.TAG, "LoginTask: Something bad happend...", e)
      }
      return true
    }

    private fun getNotifyText(result: Boolean): Int {
      return if (result == true)
        R.string.wifi_login_success
      else
        R.string.wifi_login_check_pw
    }

    override protected fun onPostExecute(login_result: Boolean) {
      val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      val contentIntent = PendingIntent.getActivity(context, 0, Intent(), 0)
      val resources = context.getResources()
      val notify_msg_id = getNotifyText(login_result)
      val n = Notification.Builder(context)
        .setContentTitle(resources.getString(R.string.app_name))
        .setContentText(resources.getString(notify_msg_id))
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentIntent(contentIntent)
        .build()
      nm.notify("CCULife_Wifi_Auto_Login", 1, n)
    }
  }

  companion object {
    val LOGIN_PAGE = "http://140.123.1.53"
    val WIFI_LOGIN_URL = "https://wlc.ccu.edu.tw/login.html"
    private val GOOGLE_IP = arrayOf(
      "202.39.143.113",
      "202.39.143.98",
      "202.39.143.123",
      "202.39.143.118",
      "202.39.143.99",
      "202.39.143.93",
      "202.39.143.84",
      "202.39.143.89",
      "202.39.143.114",
      "202.39.143.108",
      "202.39.143.103",
      "202.39.143.109",
      "202.39.143.119",
      "202.39.143.88",
      "202.39.143.94",
      "202.39.143.104"
    )
  }
}
