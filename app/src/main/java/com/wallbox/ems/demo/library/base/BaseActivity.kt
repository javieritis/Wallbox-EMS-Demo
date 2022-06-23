package com.wallbox.ems.demo.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.wallbox.ems.demo.library.ActivityNavigator
import com.wallbox.ems.demo.library.injector.modules.MainCoroutineScope
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    @Inject
    @MainCoroutineScope
    lateinit var uiScope: CoroutineScope

    @Inject
    lateinit var navigator: ActivityNavigator

    abstract val binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.setActivity(activity = this)
        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        navigator.setActivity(activity = this)
    }

    fun <T : ViewBinding> viewBinding(bindingInflater: (LayoutInflater) -> T) =
        lazy { bindingInflater.invoke(layoutInflater) }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}