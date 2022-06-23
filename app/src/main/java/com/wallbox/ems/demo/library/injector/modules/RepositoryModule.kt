package com.wallbox.ems.demo.library.injector.modules

import com.wallbox.ems.demo.domain.charger.ChargerRepository
import com.wallbox.ems.demo.domain.charger.DefaultChargerRepository
import com.wallbox.ems.demo.domain.historic_data.HistoricDataRepository
import com.wallbox.ems.demo.domain.historic_data.LocalHistoricDataRepository
import com.wallbox.ems.demo.domain.live_data.LiveDataRepository
import com.wallbox.ems.demo.domain.live_data.LocalLiveDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindLiveDataRepository(defaultLiveDataRepository: LocalLiveDataRepository): LiveDataRepository

    @Singleton
    @Binds
    fun bindHistoricDataRepository(defaultLHistoricDataRepository: LocalHistoricDataRepository): HistoricDataRepository

    @Singleton
    @Binds
    fun bindChargerRepository(defaultChargerRepository: DefaultChargerRepository): ChargerRepository
}