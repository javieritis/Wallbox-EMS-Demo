package com.wallbox.ems.demo.base

import androidx.test.espresso.intent.Intents
import com.wallbox.ems.demo.test.actor.Chart
import com.wallbox.ems.demo.test.actor.Dashboard
import com.wallbox.ems.demo.test.actor.Splash
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
open class WallboxDemoUiTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)

    @ExperimentalSerializationApi
    @Inject lateinit var chart: Chart

    @ExperimentalSerializationApi
    @Inject lateinit var splash: Splash

    @ExperimentalSerializationApi
    @Inject lateinit var dashboard: Dashboard

    @Before
    fun setUpIntents() {
        Intents.init()
        hiltRule.inject()
    }

    @After
    fun tearDownIntents() {
        Intents.release()
    }
}
