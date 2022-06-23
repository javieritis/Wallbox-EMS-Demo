package com.wallbox.ems.demo.data.live_data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class LiveData(
    @SerialName("solar_power") val solarPower: Double,
    @SerialName("quasars_power") val quasarsPower: Double,
    @SerialName("grid_power") val gridPower: Double,
    @SerialName("building_demand") val buildingDemand: Double,
    @SerialName("system_soc") val systemSoc: Double,
    @SerialName("total_energy") val totalEnergy: Double,
    @SerialName("current_energy") val currentEnergy: Double
) : Parcelable