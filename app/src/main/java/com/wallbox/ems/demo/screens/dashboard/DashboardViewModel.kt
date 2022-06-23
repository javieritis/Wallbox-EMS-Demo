package com.wallbox.ems.demo.screens.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wallbox.ems.demo.data.charger.Charger
import com.wallbox.ems.demo.data.consumption.Consumption
import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.data.live_data.LiveData
import com.wallbox.ems.demo.domain.charger.GetChargerUseCase
import com.wallbox.ems.demo.domain.historic_data.GetAmountChargedUseCase
import com.wallbox.ems.demo.domain.historic_data.GetAmountDischargedUseCase
import com.wallbox.ems.demo.domain.historic_data.GetHistoricDataUseCase
import com.wallbox.ems.demo.domain.historic_data.GetProgressConsumptionsListUseCase
import com.wallbox.ems.demo.domain.live_data.GetLiveDataUseCase
import com.wallbox.ems.demo.library.ActivityNavigator
import com.wallbox.ems.demo.library.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

enum class TimeFilter(val hours: Int) {
    LAST_HOUR(1), LAST_6_HOURS(6), LAST_12_HOURS(12), LAST_24_HOURS(24)
}

@ExperimentalSerializationApi
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val navigator: ActivityNavigator,
    private val getChargerUseCase: GetChargerUseCase,
    private val getLiveDataUseCase: GetLiveDataUseCase,
    private val getHistoricDataUseCase: GetHistoricDataUseCase,
    private val getProgressConsumptionsListUseCase: GetProgressConsumptionsListUseCase,
    private val getAmountChargedUseCase: GetAmountChargedUseCase,
    private val getAmountDischargedUseCase: GetAmountDischargedUseCase
) : BaseViewModel() {
    var filterTime = TimeFilter.LAST_HOUR

    var charger = MutableLiveData<Charger>()
    var historicData = MutableLiveData<List<HistoricData>>()
    var liveData = MutableLiveData<LiveData>()
    var consumptions = MutableLiveData<Consumption>()

    fun onFetchData() {
        uiScope.launch {
            runCatching {
                charger.value = getChargerUseCase()
                liveData.value = getLiveDataUseCase()
                historicData.value = getHistoricDataUseCase()
                generateConsumptionsModel()
            }.onFailure {
                Log.e("Error", it.message ?: "Error on function onFetchData")
            }
        }
    }

    fun onBuildingConsumptionPressed() {
        historicData.value?.let { navigator.openChartActivity(it, filterTime.hours) }
    }

    fun generateConsumptionsModel() {
        historicData.value?.let {
            consumptions.value = getProgressConsumptionsListUseCase(it, filterTime.hours)
        } ?: run {
            consumptions.value = null
        }
    }

    fun generateAmountDischarged(): Double? {
        historicData.value?.let {
            return getAmountDischargedUseCase(it, filterTime.hours)
        } ?: run {
            return null
        }
    }

    fun generateAmountCharged(): Double? {
        historicData.value?.let {
            return getAmountChargedUseCase(it, filterTime.hours)
        } ?: run {
            return null
        }
    }
}