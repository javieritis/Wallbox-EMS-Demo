package com.wallbox.ems.demo.library

import android.app.Activity
import android.content.Intent
import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.screens.chart.ChartActivity
import com.wallbox.ems.demo.screens.dashboard.DashboardActivity
import kotlinx.serialization.ExperimentalSerializationApi
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalSerializationApi::class)
@Singleton
class ActivityNavigator @Inject constructor(

) {
    companion object {
        const val EXTRA_HISTORIC_DATA = "EXTRA_HISTORIC_DATA"
        const val EXTRA_FILTER_HOURS = "EXTRA_FILTER_HOURS"
    }

    private lateinit var activityRef: WeakReference<Activity>

    fun setActivity(activity: Activity) {
        activityRef = WeakReference(activity)
    }

    fun activity(): Activity? {
        return activityRef.get()
    }

    fun openDashboardActivity() {
        activity()?.finish()
        activity()?.startActivity(Intent(activity(), DashboardActivity::class.java))
    }

    fun openChartActivity(historicData: List<HistoricData>, hours: Int) {
        activity()?.startActivity(
            Intent(activity(), ChartActivity::class.java)
                .putParcelableArrayListExtra(EXTRA_HISTORIC_DATA, ArrayList(historicData))
                .putExtra(EXTRA_FILTER_HOURS, hours)
        )
    }
}