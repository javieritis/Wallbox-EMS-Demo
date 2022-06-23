package com.wallbox.ems.demo.domain.live_data

import com.wallbox.ems.demo.data.live_data.LiveData

interface LiveDataRepository {
    suspend fun fetchLiveData(
    ): LiveData?
}