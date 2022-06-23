package com.wallbox.ems.demo.domain.live_data

import com.wallbox.ems.demo.data.live_data.LiveData
import javax.inject.Inject

class GetLiveDataUseCase @Inject constructor(
    private val liveDataRepository: LiveDataRepository
) {
    suspend operator fun invoke(): LiveData? {
        return liveDataRepository.fetchLiveData()
    }
}