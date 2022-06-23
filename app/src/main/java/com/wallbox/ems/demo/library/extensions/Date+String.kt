package com.wallbox.ems.demo.library.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun String.toDate(timeFormat: TimeFormat): Date? {
    val dateFormat = SimpleDateFormat(timeFormat.format, Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.toString(timeFormat: TimeFormat): String {
    val dateFormat = SimpleDateFormat(timeFormat.format, Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(this)
}

enum class TimeFormat(val format: String) {
    RFC3339("yyyy-MM-dd'T'HH:mm:ssZ"),
    HH_MM("HH:mm"),
}
