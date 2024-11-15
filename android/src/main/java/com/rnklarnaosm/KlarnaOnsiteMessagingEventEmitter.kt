package com.rnklarnaosm

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerModule
import com.facebook.react.uimanager.events.Event
import com.facebook.react.uimanager.events.RCTEventEmitter
import com.klarna.mobile.sdk.KlarnaMobileSDKError

class KlarnaOnsiteMessagingEventEmitter(
  private val context: ThemedReactContext
) {
  private fun emit(name: String, id: Int, params: WritableMap?) {
    context
      .getNativeModule(UIManagerModule::class.java)
      ?.eventDispatcher
      ?.dispatchEvent(object: Event<KlarnaOnsiteMessagingEventEmitter>(id) {
        override fun getEventName(): String {
          return name
        }

        override fun dispatch(rctEventEmitter: RCTEventEmitter) {
          rctEventEmitter.receiveEvent(id, name, params)
        }
      })
  }

  fun onOSMError(error: KlarnaMobileSDKError, id: Int) {
    val map = Arguments.createMap()
    map.putString("message", error.message)
    map.putString("name", error.name)
    map.putBoolean("isFatal", error.isFatal)
    emit("onOSMViewError", id, map)
  }

  fun onRender(id: Int) {
    val map = Arguments.createMap()
    emit("onHeightChange", id, map)
  }
}
