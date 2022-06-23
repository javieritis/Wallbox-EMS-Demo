package com.wallbox.ems.demo.screens.dashboard

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.wallbox.ems.demo.base.WallboxDemoUiTest
import com.wallbox.ems.demo.data.charger.Charger
import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.data.live_data.LiveData
import com.wallbox.ems.demo.test.then
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalSerializationApi::class)
@HiltAndroidTest
class DashboardActivityTest : WallboxDemoUiTest() {

//    @Module(
//        includes = [
//            FakeChargerRepoModule::class,
//        ]
//    )
//    @InstallIn(SingletonComponent::class)
//    interface TestAggregator

    @get:Rule(order = 10)
    val activityRule = ActivityScenarioRule<DashboardActivity>(
        Intent(
            ApplicationProvider.getApplicationContext(),
            DashboardActivity::class.java
        )
    )

//    private val fakeRepo = FakeChargerRepo.alwaysReturningCharger(FAKE_CHARGER)

    init {
//        FakeChargerRepo.alwaysReturningCharger(FAKE_CHARGER)
    }

    @Ignore
    @Test
    fun seesNameChargerIsFilledInCorrectView() {
//        fakeRepo.returnCharger(FAKE_CHARGER)
        then(dashboard).seesChargerName(FAKE_CHARGER.name)
    }

    private companion object {
        private const val LAST_HOUR_FILTER = 1
        private const val LAST_6_FILTER = 6
        private const val LAST_12_FILTER = 12
        private const val LAST_24_FILTER = 24

        private val FAKE_HISTORIC_DATA = arrayListOf(
            HistoricData(
                buildingActivePower = 0.0,
                gridActivePower = 0.0,
                pvActivePower = 0.0,
                quasarsActivePower = 0.0,
                timestamp = "2021-09-27T16:06:00+00:00"
            )
        )

        private val FAKE_LIVE_DATA = LiveData(
            solarPower = 0.0,
            quasarsPower = 1.1,
            gridPower = 2.2,
            buildingDemand = 3.3,
            systemSoc = 4.4,
            totalEnergy = 5.5,
            currentEnergy = 6.6
        )

        private val FAKE_CHARGER = Charger(
            connected = false,
            bidirectional = false,
            bluetooth = false,
            icon = "",
            name = "Quasar",
            registrationDate = "2021-09-27T16:06:00+00:00",
            wifi = false
        )
    }
}