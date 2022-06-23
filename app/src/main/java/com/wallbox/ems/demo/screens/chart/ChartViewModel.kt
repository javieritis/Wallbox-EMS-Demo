package com.wallbox.ems.demo.screens.chart

import androidx.lifecycle.MutableLiveData
import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.domain.historic_data.FilterHistoricDataByRangeDateUseCase
import com.wallbox.ems.demo.domain.historic_data.OrderHistoricDataByTimestampUseCase
import com.wallbox.ems.demo.library.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
@HiltViewModel
class ChartViewModel @Inject constructor(
    private val filterHistoricDataByRangeDateUseCase: FilterHistoricDataByRangeDateUseCase,
    private val orderHistoricDataByTimestampUseCase: OrderHistoricDataByTimestampUseCase
) : BaseViewModel() {

    var historicData: List<HistoricData> = emptyList()
    var hours: Int = 1

    var filteredHistoricData = MutableLiveData<List<HistoricData>>()

    fun onHistoricDataReceived(historicData: List<HistoricData>, hours: Int) {
        this.historicData = historicData
        this.hours = hours
        filteredHistoricData.value = orderHistoricDataByTimestampUseCase(filterHistoricDataByRangeDateUseCase(historicData, hours), asc = true)
    }

    fun refreshData() {
        filteredHistoricData.value = orderHistoricDataByTimestampUseCase(filterHistoricDataByRangeDateUseCase(historicData, hours), asc = true)
    }
}