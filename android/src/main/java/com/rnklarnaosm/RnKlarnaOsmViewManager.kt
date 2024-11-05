package com.rnklarnaosm

import android.graphics.Color
import android.view.View
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class RnKlarnaOsmViewManager : ViewGroupManager<KlarnaOnsiteMessagingLayout>() {
  override fun getName() = "RnKlarnaOsmView"

  override fun createViewInstance(reactContext: ThemedReactContext): KlarnaOnsiteMessagingLayout {
    return KlarnaOnsiteMessagingLayout(reactContext, KlarnaOnsiteMessagingEventEmitter(reactContext))
  }

  @ReactProp(name = "clientId")
  fun setClientId(view: KlarnaOnsiteMessagingLayout, clientId: String) {
    with(view) {
      this.clientId = clientId
    }
  }

    @ReactProp(name = "placementKey")
  fun setPlacementKey(view: KlarnaOnsiteMessagingLayout, placementKey: String) {
    with(view) {
      this.placementKey = placementKey
    }
  }

  @ReactProp(name = "locale")
  fun setLocale(view: KlarnaOnsiteMessagingLayout, locale: String) {
    with(view) {
      this.locale = locale
    }
  }

  @ReactProp(name = "environment")
  fun setEnvironment(view: KlarnaOnsiteMessagingLayout, environment: Int) {
    with(view) {
      this.environment = environment
    }
  }

  @ReactProp(name = "region")
  fun setRegion(view: KlarnaOnsiteMessagingLayout, region: Int) {
    with(view) {
      this.region = region
    }
  }

  @ReactProp(name = "purchaseAmount")
  fun setPurchaseAmount(view: KlarnaOnsiteMessagingLayout, purchaseAmount: Int?) {
    with(view) {
      this.purchaseAmount = purchaseAmount
    }
  }

  override fun onAfterUpdateTransaction(view: KlarnaOnsiteMessagingLayout) {
    view.setupOSMView()
  }

  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any> {
    return mutableMapOf(
      "onOSMViewError" to mutableMapOf("registrationName" to "onOSMViewError"),
      "onHeightChange" to mutableMapOf("registrationName" to "onHeightChange")
    )
  }

  override fun createViewInstance(reactContext: ThemedReactContext): KlarnaOnsiteMessagingLayout {
    return KlarnaOnsiteMessagingLayout(reactContext, KlarnaOnsiteMessagingEventEmitter(reactContext))
  }
}
