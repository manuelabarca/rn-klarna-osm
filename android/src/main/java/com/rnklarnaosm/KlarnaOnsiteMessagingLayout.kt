package com.rnklarnaosm

import android.util.Log
import android.widget.LinearLayout
import android.view.ViewGroup
import com.facebook.react.uimanager.ThemedReactContext
import com.klarna.mobile.sdk.api.osm.*
import com.klarna.mobile.sdk.api.KlarnaEnvironment
import com.klarna.mobile.sdk.api.KlarnaRegion

private const val TAG = "OsmViewSetup"

class KlarnaOnsiteMessagingLayout(
  private val reactContext: ThemedReactContext,
  private val eventEmitter: KlarnaOnsiteMessagingEventEmitter
): LinearLayout(reactContext) {
  val osmView: KlarnaOSMView = KlarnaOSMView(reactContext)

  init {
    val layoutParams = ViewGroup.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT
    )
    this.addView(osmView, layoutParams)

    osmView.hostActivity = reactContext.currentActivity
  }

  fun setupOSMView() {
    osmView.hostActivity = reactContext.currentActivity
    osmView.render(RenderResult { error ->
      if (error != null) {
        Log.e(TAG, "OSM Render Error: ${error.message}")
        eventEmitter.onOSMError(error, id)
      } else {
        Log.i(TAG, "OSM Rendered Successfully")
        eventEmitter.onRender(id)
      }
    })
  }
}
