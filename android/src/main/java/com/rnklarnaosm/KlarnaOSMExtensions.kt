package com.rnklarnaosm


import com.klarna.mobile.sdk.api.osm.KlarnaOSMEnvironment
import com.klarna.mobile.sdk.api.osm.KlarnaOSMRegion
import kotlin.reflect.KClass

fun KClass<KlarnaOSMEnvironment>.fromRawValue(value: Int): KlarnaOSMEnvironment =
  when(value) {
    0 -> KlarnaOSMEnvironment.DEMO
    1 -> KlarnaOSMEnvironment.PRODUCTION
    2 -> KlarnaOSMEnvironment.PLAYGROUND
    else -> KlarnaOSMEnvironment.DEMO
  }

fun KClass<KlarnaOSMRegion>.fromRawValue(value: Int): KlarnaOSMRegion =
  when(value) {
    0 -> KlarnaOSMRegion.EU
    1 -> KlarnaOSMRegion.NA
    2 -> KlarnaOSMRegion.OC
    else -> KlarnaOSMRegion.EU
  }
