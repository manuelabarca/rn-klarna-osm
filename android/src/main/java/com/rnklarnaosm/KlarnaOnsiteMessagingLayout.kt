package com.rnklarnaosm

import android.util.Log
import android.widget.LinearLayout
import com.facebook.react.uimanager.ThemedReactContext
import com.klarna.mobile.sdk.api.osm.*
import com.klarna.mobile.sdk.api.KlarnaEnvironment
import com.klarna.mobile.sdk.api.KlarnaRegion

private const val TAG = "OsmViewSetup"

class KlarnaOnsiteMessagingLayout(
  private val reactContext: ThemedReactContext,
  private val eventEmitter: KlarnaOnsiteMessagingEventEmitter
): LinearLayout(reactContext) {
  val osmView: KlarnaOSMView

  init {
    inflate(context, R.layout.klarna_onsite_messaging_layout, this)

    osmView = findViewById(R.id.klarnaOsmView)
    osmView.hostActivity = reactContext.currentActivity
  }


  fun setupOSMView() {

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
