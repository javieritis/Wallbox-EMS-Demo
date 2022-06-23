package com.wallbox.ems.demo.domain.historic_data

import com.wallbox.ems.demo.data.historic_data.HistoricData
import javax.inject.Inject

class OrderHistoricDataByTimestampUseCase @Inject constructor() {
    operator fun invoke(historicData: List<HistoricData>, asc: Boolean): List<HistoricData> {
        return if (asc) historicData.sortedBy { it.timestamp } else historicData.sortedByDescending { it.timestamp }
    }
}