package com.wallbox.ems.demo.test.actor

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.wallbox.ems.demo.R
import kotlinx.serialization.ExperimentalSerializationApi
import org.hamcrest.CoreMatchers.allOf
import javax.inject.Inject

@ExperimentalSerializationApi
class Dashboard @Inject constructor() {
    fun seesChargerName(@Suppress("UNUSED_PARAMETER") chargerName: String) {
//        onView(withId(R.id.widgetInfoCharger))
//            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(allOf(withId(R.id.titleCharger), isDescendantOfA(withId(R.id.widgetInfoCharger)))).check(matches(
            withText(chargerName)
        ))
    }
}