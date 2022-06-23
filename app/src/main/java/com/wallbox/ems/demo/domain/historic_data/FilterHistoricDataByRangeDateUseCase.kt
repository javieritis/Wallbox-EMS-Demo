package com.wallbox.ems.demo.domain.historic_data

import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.library.extensions.TimeFormat
import com.wallbox.ems.demo.library.extensions.minusHours
import com.wallbox.ems.demo.library.extensions.toDate
import javax.inject.Inject

class FilterHistoricDataByRangeDateUseCase @Inject constructor() {
    operator fun invoke(historicData: List<HistoricData>, hours: Int): List<HistoricData> {
        val dateStart = historicData.first().timestamp.toDate(TimeFormat.RFC3339)
        val dateEnd = dateStart?.minusHours(hours)
        val result = historicData.filter {
            (it.timestamp.toDate(TimeFormat.RFC3339)!!.before(dateStart) || it.timestamp.toDate(
                TimeFormat.RFC3339
            )!!.equals(dateStart)) && it.timestamp.toDate(TimeFormat.RFC3339)!!.after(dateEnd)
        }
        return result
    }
}