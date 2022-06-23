package com.wallbox.ems.demo.screens.splash

import com.wallbox.ems.demo.library.ActivityNavigator
import com.wallbox.ems.demo.library.base.BaseViewModel
import com.wallbox.ems.demo.library.extensions.postDelayedInMainThread
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: ActivityNavigator
) : BaseViewModel() {
    fun onCreate() {
        postDelayedInMainThread(2500) {
            navigator.openDashboardActivity()
        }
    }
}