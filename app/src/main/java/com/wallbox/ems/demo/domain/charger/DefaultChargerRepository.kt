package com.wallbox.ems.demo.domain.charger

import com.wallbox.ems.demo.data.charger.Charger
import javax.inject.Inject

class DefaultChargerRepository @Inject constructor() : ChargerRepository {

    override suspend fun fetchCharger(): Charger {
        return Charger(
            name = "Quasar",
            registrationDate = "2021-09-27T16:06:00+00:00",
            icon = "ic_quasar",
            connected = true,
            bidirectional = true,
            bluetooth = true,
            wifi = true
        )
    }
}