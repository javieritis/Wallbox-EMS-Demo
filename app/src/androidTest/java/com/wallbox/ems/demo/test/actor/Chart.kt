package com.wallbox.ems.demo.test.actor

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.wallbox.ems.demo.R
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class Chart @Inject constructor() {
    fun checkIfButtonLastHourIsChecked() {
        onView(withId(R.id.filterTimeLastHour))
            .check(matches(isChecked()))
    }

    fun checkIfButtonLast6IsChecked() {
        onView(withId(R.id.filterTimeLast6Hours))
            .check(matches(isChecked()))
    }

    fun checkIfButtonLast12IsChecked() {
        onView(withId(R.id.filterTimeLast12Hours))
            .check(matches(isChecked()))
    }

    fun checkIfButtonLast24IsChecked() {
        onView(withId(R.id.filterTimeLast24Hours))
            .check(matches(isChecked()))
    }
}