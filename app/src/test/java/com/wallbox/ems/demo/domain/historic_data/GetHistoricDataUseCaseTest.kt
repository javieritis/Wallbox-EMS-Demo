package com.wallbox.ems.demo.domain.historic_data

import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.data.live_data.LiveData
import com.wallbox.ems.demo.fake.FakeHistoricDataRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.Test

@ExperimentalCoroutinesApi
class GetHistoricDataUseCaseTest {
    private val fakeHistoricDataRepo = FakeHistoricDataRepo.aFakeHistoricDataRepo()

    private val useCase = GetHistoricDataUseCase(
        fakeHistoricDataRepo
    )

    @Test
    fun check_return_empty_historic_data() = runBlocking {
        fakeHistoricDataRepo.returnHistoricDataEmpty()

        MatcherAssert.assertThat(useCase(), equalTo(emptyList()))
    }

    @Test
    fun check_return_same_historic_data() = runBlocking {
        fakeHistoricDataRepo.returnHistoricData(HISTORIC_DATA)

        MatcherAssert.assertThat(useCase(), equalTo(HISTORIC_DATA))
    }

    private companion object {
        private val HISTORIC_DATA = listOf(
            HistoricData(
                buildingActivePower = 0.0,
                gridActivePower = 0.0,
                pvActivePower = 0.0,
                quasarsActivePower = 0.0,
                timestamp = "today"
            )
        )
    }
}
