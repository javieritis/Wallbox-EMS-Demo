package com.wallbox.ems.demo.domain.historic_data

import com.wallbox.ems.demo.data.historic_data.HistoricData

interface HistoricDataRepository {
    suspend fun fetchHistoricData(
    ): List<HistoricData>
}