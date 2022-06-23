package com.wallbox.ems.demo.domain.historic_data

import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.library.extensions.*
import javax.inject.Inject

class GetAmountDischargedUseCase @Inject constructor() {
    operator fun invoke(historicData: List<HistoricData>, hours: Int): Double {
        val dateStart = historicData.first().timestamp.toDate(TimeFormat.RFC3339)
        val dateEnd = dateStart?.minusHours(hours)
        val resultPositive = historicData.filter {
            (it.timestamp.toDate(TimeFormat.RFC3339)!!.before(dateStart) || it.timestamp.toDate(
                TimeFormat.RFC3339
            )!!.equals(dateStart)) && it.timestamp.toDate(TimeFormat.RFC3339)!!.after(dateEnd)
                    && it.quasarsActivePower < 0
        }
        val finalAveragePositive = resultPositive.map { it.quasarsActivePower }.average()
        return (finalAveragePositive * hours).roundTo(1)
    }
}