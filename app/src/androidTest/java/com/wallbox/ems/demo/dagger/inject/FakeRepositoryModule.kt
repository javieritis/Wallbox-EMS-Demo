package com.wallbox.ems.demo.dagger.inject

import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck


@Module
@DisableInstallInCheck
interface FakeChargerRepoModule {
//    @Singleton
//    @Binds
//    fun bindsChargerRepo(fakeChargerRepo: FakeChargerRepo): ChargerRepository
}