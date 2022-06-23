package com.wallbox.ems.demo.fake

import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.data.live_data.LiveData
import com.wallbox.ems.demo.domain.historic_data.HistoricDataRepository
import kotlinx.coroutines.flow.MutableStateFlow

class FakeHistoricDataRepo private constructor() : HistoricDataRepository {
    fun returnHistoricDataEmpty() {
        flow.value = emptyList()
    }

    fun returnHistoricData(historicData: List<HistoricData>) {
        flow.value = historicData
    }

    companion object {
        private val flow = MutableStateFlow<List<HistoricData>>(emptyList())

        fun aFakeHistoricDataRepo() = FakeHistoricDataRepo()

        fun alwaysReturningHistoricData(historicData: List<HistoricData>) =
            FakeHistoricDataRepo().apply {
                flow.value = historicData
            }
    }

    override suspend fun fetchHistoricData(): List<HistoricData> {
        return flow.value
    }
}