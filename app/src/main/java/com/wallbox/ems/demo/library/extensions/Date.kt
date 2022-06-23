package com.wallbox.ems.demo.library.extensions

import java.util.*

fun currentDate(): Date = Calendar.getInstance().time

fun Date.plusYears(years: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.plusYears(years)
    return calendar.time
}

fun Date.minusDays(days: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_MONTH, -days)
    return calendar.time
}

fun Date.minusHours(hours: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.HOUR_OF_DAY, -hours)
    return calendar.time
}