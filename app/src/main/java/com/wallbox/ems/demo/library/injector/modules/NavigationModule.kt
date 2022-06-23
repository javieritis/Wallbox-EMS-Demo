package com.wallbox.ems.demo.library.injector.modules

import com.wallbox.ems.demo.library.ActivityNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideActivityNavigator(): ActivityNavigator {
        return ActivityNavigator()
    }
}