package com.wallbox.ems.demo.screens.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.wallbox.ems.demo.databinding.ActivitySplashBinding
import com.wallbox.ems.demo.library.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val binding by viewBinding(ActivitySplashBinding::inflate)

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreate()
    }
}