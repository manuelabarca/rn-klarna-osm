package com.rnklarnaosm

import android.graphics.Color
import android.view.View
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.klarna.mobile.sdk.api.KlarnaEnvironment
import com.klarna.mobile.sdk.api.KlarnaRegion

class RnKlarnaOsmViewManager : ViewGroupManager<KlarnaOnsiteMessagingLayout>() {
  override fun getName() = "RnKlarnaOsmView"


  @ReactProp(name = "clientId")
  fun setClientId(view: KlarnaOnsiteMessagingLayout, clientId: String) {
    with(view) {
      this.osmView.clientId = clientId
    }
  }

    @ReactProp(name = "placementKey")
  fun setPlacementKey(view: KlarnaOnsiteMessagingLayout, placementKey: String) {
    with(view) {
      this.osmView.placementKey = placementKey
    }
  }

  @ReactProp(name = "locale")
  fun setLocale(view: KlarnaOnsiteMessagingLayout, locale: String) {
    with(view) {
      this.osmView.locale = locale
    }
  }

  @ReactProp(name = "environment")
  fun setEnvironment(view: KlarnaOnsiteMessagingLayout, environment: Int) {
    with(view) {
      this.osmView.environment = KlarnaEnvironment::class.fromRawValue(environment)
    }
  }

  @ReactProp(name = "region")
  fun setRegion(view: KlarnaOnsiteMessagingLayout, region: Int) {
    with(view) {
      this.osmView.region = KlarnaRegion::class.fromRawValue(region)
    }
  }

  @ReactProp(name = "purchaseAmount")
  fun setPurchaseAmount(view: KlarnaOnsiteMessagingLayout, purchaseAmount: Int?) {
    with(view) {
      this.osmView.purchaseAmount = purchaseAmount?.toLong()
    }
  }

  override fun onAfterUpdateTransaction(view: KlarnaOnsiteMessagingLayout) {
    super.onAfterUpdateTransaction(view)
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
