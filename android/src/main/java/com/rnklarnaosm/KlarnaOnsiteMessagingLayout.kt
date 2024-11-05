package com.rnklarnaosm

import android.util.Log
import android.widget.LinearLayout
import com.facebook.react.uimanager.ThemedReactContext
import com.klarna.mobile.sdk.api.osm.*

class KlarnaOnsiteMessagingLayout(
  private val reactContext: ThemedReactContext,
  private val eventEmitter: KlarnaOnsiteMessagingEventEmitter
): LinearLayout(reactContext) {
  private val osmView: KlarnaOSMView

  init {
    inflate(context, R.layout.klarna_onsite_messaging_layout, this)

    osmView = findViewById(R.id.klarnaOsmView)
    osmView.hostActivity = reactContext.currentActivity
  }

  var clientId: String = ""
  var placementKey: String = ""
  var locale: String = ""
  var region: Int = 0
  var environment: Int = 0
  var purchaseAmount: Int? = null

  fun setupOSMView() {
    osmView.clientId = clientId
    osmView.placementKey = placementKey
    osmView.locale = locale
    osmView.environment = KlarnaOSMEnvironment::class.fromRawValue(environment)
    osmView.region = KlarnaOSMRegion::class.fromRawValue(region)
    osmView.purchaseAmount = purchaseAmount?.toLong()
    osmView.hostActivity = reactContext.currentActivity

    osmView.render(RenderResult {
      if(it != null) {
        eventEmitter.onOSMError(it, id)
      } else {
        eventEmitter.onRender(id)
      }
    })
  }
}
