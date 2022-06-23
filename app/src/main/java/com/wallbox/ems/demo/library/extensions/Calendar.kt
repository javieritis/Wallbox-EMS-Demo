package com.wallbox.ems.demo.library.extensions

import java.util.Calendar

fun Calendar.getDayOfMonth(): Int = this.get(Calendar.DAY_OF_MONTH)

fun Calendar.getMonth(): Int = this.get(Calendar.MONTH)

fun Calendar.getYear(): Int = this.get(Calendar.YEAR)

fun Calendar.plusYears(years: Int): Calendar {
    this.add(Calendar.YEAR, years)
    return this
}
