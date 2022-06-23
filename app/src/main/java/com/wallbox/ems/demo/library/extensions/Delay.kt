package com.wallbox.ems.demo.library.extensions

import android.os.Handler
import android.os.Looper

fun postDelayedInMainThread(millis: Long, doAfter: (() -> Unit)) {
    Handler(Looper.getMainLooper()).postDelayed(doAfter, millis)
}