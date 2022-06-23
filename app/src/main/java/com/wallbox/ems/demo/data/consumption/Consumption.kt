package com.wallbox.ems.demo.data.consumption

data class Consumption(
    val buildingConsumption: Double,
    val sourcesConsumptions: List<SourceConsumption>
)

data class SourceConsumption(
    val progress: Double,
    val icon: String
)