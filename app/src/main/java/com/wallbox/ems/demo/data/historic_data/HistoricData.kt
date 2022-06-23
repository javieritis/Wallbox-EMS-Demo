package com.wallbox.ems.demo.data.historic_data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class HistoricData(
    @SerialName("building_active_power") val buildingActivePower: Double,
    @SerialName("grid_active_power") val gridActivePower: Double,
    @SerialName("pv_active_power") val pvActivePower: Double,
    @SerialName("quasars_active_power") val quasarsActivePower: Double,
    @SerialName("timestamp") val timestamp: String
) : Parcelable