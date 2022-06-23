package com.wallbox.ems.demo.library.extensions

fun List<Double>.average(): Double {
    var sum = 0.0
    for (i in this) {
        sum += i.toLong()
    }
    return if (this.isNotEmpty()) sum / this.size else 0.0
}