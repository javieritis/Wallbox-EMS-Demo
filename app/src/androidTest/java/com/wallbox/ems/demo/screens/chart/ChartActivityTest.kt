package com.wallbox.ems.demo.screens.chart

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.wallbox.ems.demo.base.WallboxDemoUiTest
import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.library.ActivityNavigator.Companion.EXTRA_FILTER_HOURS
import com.wallbox.ems.demo.library.ActivityNavigator.Companion.EXTRA_HISTORIC_DATA
import com.wallbox.ems.demo.test.then
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Test

@OptIn(ExperimentalSerializationApi::class)
@HiltAndroidTest
class ChartActivityTest : WallboxDemoUiTest() {

    private val intentLastHour =
        Intent(ApplicationProvider.getApplicationContext(), ChartActivity::class.java)
            .putParcelableArrayListExtra(EXTRA_HISTORIC_DATA, DEFAULT_HISTORIC_DATA)
            .putExtra(EXTRA_FILTER_HOURS, LAST_HOUR_FILTER)

    private val intentLast6Hours =
        Intent(ApplicationProvider.getApplicationContext(), ChartActivity::class.java)
            .putParcelableArrayListExtra(EXTRA_HISTORIC_DATA, DEFAULT_HISTORIC_DATA)
            .putExtra(EXTRA_FILTER_HOURS, LAST_6_FILTER)

    private val intentLast12Hours =
        Intent(ApplicationProvider.getApplicationContext(), ChartActivity::class.java)
            .putParcelableArrayListExtra(EXTRA_HISTORIC_DATA, DEFAULT_HISTORIC_DATA)
            .putExtra(EXTRA_FILTER_HOURS, LAST_12_FILTER)

    private val intentLast24Hours =
        Intent(ApplicationProvider.getApplicationContext(), ChartActivity::class.java)
            .putParcelableArrayListExtra(EXTRA_HISTORIC_DATA, DEFAULT_HISTORIC_DATA)
            .putExtra(EXTRA_FILTER_HOURS, LAST_24_FILTER)

    @Test
    fun seesIsFilterLastHourIsChecked() {
        ActivityScenario.launch<ChartActivity>(intentLastHour)
        then(chart).checkIfButtonLastHourIsChecked()
    }

    @Test
    fun seesIsFilterLast6IsChecked() {
        ActivityScenario.launch<ChartActivity>(intentLast6Hours)
        then(chart).checkIfButtonLast6IsChecked()
    }

    @Test
    fun seesIsFilterLast12IsChecked() {
        ActivityScenario.launch<ChartActivity>(intentLast12Hours)
        then(chart).checkIfButtonLast12IsChecked()
    }

    @Test
    fun seesIsFilterLast24IsChecked() {
        ActivityScenario.launch<ChartActivity>(intentLast24Hours)
        then(chart).checkIfButtonLast24IsChecked()
    }

    private companion object {
        private const val LAST_HOUR_FILTER = 1
        private const val LAST_6_FILTER = 6
        private const val LAST_12_FILTER = 12
        private const val LAST_24_FILTER = 24

        private val DEFAULT_HISTORIC_DATA = arrayListOf(
            HistoricData(
                buildingActivePower = 0.0,
                gridActivePower = 0.0,
                pvActivePower = 0.0,
                quasarsActivePower = 0.0,
                timestamp = "2021-09-27T16:06:00+00:00"
            )
        )
    }
}