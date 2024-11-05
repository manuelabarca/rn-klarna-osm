package com.rnklarnaosm


import com.klarna.mobile.sdk.api.KlarnaEnvironment
import com.klarna.mobile.sdk.api.KlarnaRegion
import kotlin.reflect.KClass

fun KClass<KlarnaEnvironment>.fromRawValue(value: Int): KlarnaEnvironment =
  when(value) {
    0 -> KlarnaEnvironment.DEMO
    1 -> KlarnaEnvironment.PLAYGROUND
    2 -> KlarnaEnvironment.PRODUCTION
    else -> KlarnaEnvironment.DEMO
  }

fun KClass<KlarnaRegion>.fromRawValue(value: Int): KlarnaRegion =
  when(value) {
    0 -> KlarnaRegion.EU
    1 -> KlarnaRegion.NA
    2 -> KlarnaRegion.OC
    else -> KlarnaRegion.EU
  }
