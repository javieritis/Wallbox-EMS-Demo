package com.wallbox.ems.demo.domain.historic_data

import com.wallbox.ems.demo.data.historic_data.HistoricData
import javax.inject.Inject

class GetHistoricDataUseCase @Inject constructor(
    private val historicDataRepository: HistoricDataRepository
) {
    suspend operator fun invoke(): List<HistoricData> {
        return historicDataRepository.fetchHistoricData()
    }
}