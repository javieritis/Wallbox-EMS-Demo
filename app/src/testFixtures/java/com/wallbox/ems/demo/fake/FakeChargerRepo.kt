package com.wallbox.ems.demo.fake

import com.wallbox.ems.demo.data.charger.Charger
import com.wallbox.ems.demo.domain.charger.ChargerRepository
import kotlinx.coroutines.flow.MutableStateFlow

class FakeChargerRepo private constructor() : ChargerRepository {
    fun returnCharger(charger: Charger) {
        flow.value = charger
    }

    companion object {
        private val flow = MutableStateFlow<Charger>(
            Charger(
                connected = false,
                bidirectional = false,
                bluetooth = false,
                icon = "",
                name = "",
                registrationDate = "",
                wifi = false
            )
        )

        fun aFakeChargerRepo() = FakeChargerRepo()

        fun alwaysReturningCharger(charger: Charger) =
            FakeChargerRepo().apply {
                flow.value = charger
            }
    }

    override suspend fun fetchCharger(): Charger {
        return flow.value
    }
}