package com.wallbox.ems.demo.domain.charger

import com.wallbox.ems.demo.data.charger.Charger
import javax.inject.Inject

class GetChargerUseCase @Inject constructor(
    private val chargerRepository: ChargerRepository
) {
    suspend operator fun invoke(): Charger {
        return chargerRepository.fetchCharger()
    }
}