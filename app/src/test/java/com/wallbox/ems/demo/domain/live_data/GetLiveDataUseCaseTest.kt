package com.wallbox.ems.demo.domain.live_data

import com.wallbox.ems.demo.data.live_data.LiveData
import com.wallbox.ems.demo.fake.FakeLiveDataRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.Test

@ExperimentalCoroutinesApi
class GetLiveDataUseCaseTest {
    private val fakeLiveDataRepo = FakeLiveDataRepo.aFakeLiveDataRepo()

    private val useCase = GetLiveDataUseCase(
        fakeLiveDataRepo
    )

    @Test
    fun check_return_null_live_data() = runBlocking {
        fakeLiveDataRepo.returnLiveDataNull()

        MatcherAssert.assertThat(useCase(), equalTo(null))
    }

    @Test
    fun check_return_same_live_data() = runBlocking {
        fakeLiveDataRepo.returnLiveData(LIVE_DATA)

        MatcherAssert.assertThat(useCase(), equalTo(LIVE_DATA))
    }

    private companion object {
        private val LIVE_DATA = LiveData(
            solarPower = 0.0,
            quasarsPower = 1.1,
            gridPower = 2.2,
            buildingDemand = 3.3,
            systemSoc = 4.4,
            totalEnergy = 5.5,
            currentEnergy = 6.6
        )
    }
}
