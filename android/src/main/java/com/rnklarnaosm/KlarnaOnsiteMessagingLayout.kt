package com.rnklarnaosm

import android.util.Log
import android.widget.LinearLayout
import com.facebook.react.uimanager.ThemedReactContext
import com.klarna.mobile.sdk.api.osm.*
import com.klarna.mobile.sdk.api.KlarnaEnvironment
import com.klarna.mobile.sdk.api.KlarnaRegion


class KlarnaOnsiteMessagingLayout(
  private val reactContext: ThemedReactContext,
  private val eventEmitter: KlarnaOnsiteMessagingEventEmitter
): LinearLayout(reactContext) {
  private val osmView: KlarnaOSMView
  private const val TAG = "OsmViewSetup"

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
    osmView.environment = KlarnaEnvironment::class.fromRawValue(environment)
    osmView.region = KlarnaRegion::class.fromRawValue(region)
    osmView.purchaseAmount = purchaseAmount?.toLong()
    osmView.hostActivity = reactContext.currentActivity

      Log.d(TAG, "clientId: $clientId")
    Log.d(TAG, "placementKey: $placementKey")
    Log.d(TAG, "locale: $locale")
    Log.d(TAG, "environment: ${KlarnaEnvironment::class.fromRawValue(environment)}")
    Log.d(TAG, "region: ${KlarnaRegion::class.fromRawValue(region)}")
    Log.d(TAG, "purchaseAmount: ${purchaseAmount?.toLong()}")
    Log.d(TAG, "hostActivity: ${reactContext.currentActivity}")

    osmView.render(RenderResult {
      if(it != null) {
        eventEmitter.onOSMError(it, id)
      } else {
        eventEmitter.onRender(id)
      }
    })
  }
}
