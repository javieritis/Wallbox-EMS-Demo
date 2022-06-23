package com.wallbox.ems.demo.test.actor

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.wallbox.ems.demo.R
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class Splash @Inject constructor() {
    fun checkIfImageSplashIsVisible() {
        onView(withId(R.id.imageSplash)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}