package com.wallbox.ems.demo.library.base


import androidx.lifecycle.ViewModel
import com.wallbox.ems.demo.library.injector.modules.MainCoroutineScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    @MainCoroutineScope
    lateinit var uiScope: CoroutineScope
}