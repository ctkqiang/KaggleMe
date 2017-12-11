package com.glaucus.kaggleme.extension

import android.util.Log

/**
 * Created by glaucus on 2017/12/11.
 */
const val DEBUG = true

fun Log.info(message: Any) {
    Log.i(this.javaClass.simpleName, message.toString())
}

fun Log.debug(message: Any) {
    Log.d(this.javaClass.simpleName, message.toString())
}

fun Log.warn(message: Any) {
    Log.w(this.javaClass.simpleName, message.toString())
}

fun Log.error(message: Any) {
    Log.e(this.javaClass.simpleName, message.toString())
}