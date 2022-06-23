package com.wallbox.ems.demo.screens.splash

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.wallbox.ems.demo.base.WallboxDemoUiTest
import com.wallbox.ems.demo.test.then
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalSerializationApi::class)
@HiltAndroidTest
class SplashActivityTest : WallboxDemoUiTest() {

    @get:Rule(order = 10)
    val activityRule = ActivityScenarioRule<SplashActivity>(
        Intent(
            ApplicationProvider.getApplicationContext(),
            SplashActivity::class.java
        )
    )

    @Test
    fun seesIsImageSplashIsVisible() {
        then(splash).checkIfImageSplashIsVisible()
    }
}