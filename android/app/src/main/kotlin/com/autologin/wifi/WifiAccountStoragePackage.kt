package com.autologin.wifi

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.JavaScriptModule
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

import java.util.ArrayList
import java.util.Arrays

class WifiAccountStoragePackage:ReactPackage {
  override fun createJSModules(): List<Class<out JavaScriptModule>> {
    return emptyList<Class<out JavaScriptModule>>()
  }

  override fun createViewManagers(reactContext:ReactApplicationContext): List<ViewManager<*, *>> {
    return emptyList<ViewManager<*, *>>()
  }

  override fun createNativeModules(reactContext:ReactApplicationContext): List<NativeModule> {
    val modules = ArrayList<NativeModule>()
    modules.add(WifiAccountStorageModule(reactContext))
    return modules
  }
}
