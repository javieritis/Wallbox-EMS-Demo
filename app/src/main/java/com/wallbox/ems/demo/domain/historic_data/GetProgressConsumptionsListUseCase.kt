package com.wallbox.ems.demo.domain.historic_data

import com.wallbox.ems.demo.data.consumption.Consumption
import com.wallbox.ems.demo.data.consumption.SourceConsumption
import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.library.extensions.TimeFormat
import com.wallbox.ems.demo.library.extensions.average
import com.wallbox.ems.demo.library.extensions.minusHours
import com.wallbox.ems.demo.library.extensions.toDate
import javax.inject.Inject

class GetProgressConsumptionsListUseCase @Inject constructor() {
    operator fun invoke(historicData: List<HistoricData>, hours: Int): Consumption {
        val dateStart = historicData.first().timestamp.toDate(TimeFormat.RFC3339)
        val dateEnd = dateStart?.minusHours(hours)
        val result = historicData.filter {
            (it.timestamp.toDate(TimeFormat.RFC3339)!!.before(dateStart) || it.timestamp.toDate(
                TimeFormat.RFC3339
            )!!.equals(dateStart)) && it.timestamp.toDate(TimeFormat.RFC3339)!!.after(dateEnd)
        }
        val buildingDemandAverage = result.map { it.buildingActivePower }.average()

        val gridAverage =
            percentage(buildingDemandAverage, result.map { it.gridActivePower }.average())
        val pvAverage = percentage(buildingDemandAverage, result.map { it.pvActivePower }.average())
        val quasarAverage =
            percentage(buildingDemandAverage, result.map { it.quasarsActivePower }.average())
        return Consumption(
            buildingDemandAverage, listOf(
                SourceConsumption(gridAverage, "ic_plug"),
                SourceConsumption(pvAverage, "ic_pv"),
                SourceConsumption(quasarAverage, "ic_quasar")
            )
        )
    }

    private fun percentage(total: Double, count: Double): Double {
        return (count / total) * 100
    }
}