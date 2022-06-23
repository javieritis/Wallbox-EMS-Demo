package com.wallbox.ems.demo.fake

import com.wallbox.ems.demo.data.live_data.LiveData
import com.wallbox.ems.demo.domain.live_data.LiveDataRepository
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLiveDataRepo private constructor() : LiveDataRepository {
    fun returnLiveDataNull() {
        flow.value = null
    }

    fun returnLiveData(liveData: LiveData) {
        flow.value = liveData
    }

    companion object {
        private val flow = MutableStateFlow<LiveData?>(null)

        fun aFakeLiveDataRepo() = FakeLiveDataRepo()

        fun alwaysReturningLiveData(liveData: LiveData) =
            FakeLiveDataRepo().apply {
                flow.value = liveData
            }
    }

    override suspend fun fetchLiveData(): LiveData? {
        return flow.value
    }
}