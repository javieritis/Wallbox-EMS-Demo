package com.wallbox.ems.demo.domain.charger

import com.wallbox.ems.demo.data.charger.Charger
import com.wallbox.ems.demo.fake.FakeChargerRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.Test

@ExperimentalCoroutinesApi
class GetChargerUseCaseTest {
    private val fakeChargerRepo = FakeChargerRepo.aFakeChargerRepo()

    private val useCase = GetChargerUseCase(
        fakeChargerRepo
    )

    @Test
    fun check_return_same_charger() = runBlocking {
        fakeChargerRepo.returnCharger(FAKE_CHARGER)

        MatcherAssert.assertThat(useCase(), equalTo(FAKE_CHARGER))
    }

    private companion object {
        private val FAKE_CHARGER = Charger(
            connected = false,
            bidirectional = false,
            bluetooth = false,
            icon = "",
            name = "",
            registrationDate = "",
            wifi = false
        )
    }
}
