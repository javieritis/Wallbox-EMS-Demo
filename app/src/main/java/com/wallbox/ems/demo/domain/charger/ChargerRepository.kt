package com.wallbox.ems.demo.domain.charger

import com.wallbox.ems.demo.data.charger.Charger

interface ChargerRepository {
    suspend fun fetchCharger(
    ): Charger
}