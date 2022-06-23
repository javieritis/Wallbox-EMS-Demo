package com.wallbox.ems.demo.screens.dashboard

import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.wallbox.ems.demo.R
import com.wallbox.ems.demo.data.charger.Charger
import com.wallbox.ems.demo.data.consumption.Consumption
import com.wallbox.ems.demo.data.live_data.LiveData
import com.wallbox.ems.demo.databinding.ActivityDashboardBinding
import com.wallbox.ems.demo.library.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.ExperimentalSerializationApi


@ExperimentalSerializationApi
@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {
    override val binding by viewBinding(ActivityDashboardBinding::inflate)

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.filterTimeLastHour.setOnOnCheckedChangeListener(checkedChangeListener)
        binding.filterTimeLast6Hours.setOnOnCheckedChangeListener(checkedChangeListener)
        binding.filterTimeLast12Hours.setOnOnCheckedChangeListener(checkedChangeListener)
        binding.filterTimeLast24Hours.setOnOnCheckedChangeListener(checkedChangeListener)

        binding.mainContentDashboard.isVisible = false
        binding.layoutPlaceholder.shimmerViewContainer.isVisible = true
        viewModel.onFetchData()

        viewModel.charger.observe(this) {
            setupChargerInfoWidget(it)
        }
        viewModel.consumptions.observe(this) {
            setupConsumptionWidget(it)
        }
        viewModel.liveData.observe(this) {
            setupLiveDataWidget(it)
        }
        viewModel.historicData.observe(this) {
            binding.layoutPlaceholder.shimmerViewContainer.isVisible = false
            binding.mainContentDashboard.isVisible = true
            setupAmountsWidgets()
        }

        binding.widgetInfoCharger.charger = viewModel.charger.value
        binding.headerFilterTime.text = resources.getQuantityString(R.plurals.filter_time_title, 1)

        binding.widgetBuildingConsumption.setOnClickListener { viewModel.onBuildingConsumptionPressed() }
        binding.widgetBuildingConsumption.onClickWidgetListener {
            viewModel.onBuildingConsumptionPressed()
        }
    }

    private val checkedChangeListener =
        CompoundButton.OnCheckedChangeListener { compoundButton: CompoundButton, _: Boolean ->
            when (compoundButton.id) {
                R.id.filterTimeLastHour -> {
                    viewModel.filterTime = TimeFilter.LAST_HOUR
                    binding.headerFilterTime.text =
                        resources.getQuantityString(R.plurals.filter_time_title, 1, 1)
                }
                R.id.filterTimeLast6Hours -> {
                    viewModel.filterTime = TimeFilter.LAST_6_HOURS
                    binding.headerFilterTime.text =
                        resources.getQuantityString(R.plurals.filter_time_title, 6, 6)
                }
                R.id.filterTimeLast12Hours -> {
                    viewModel.filterTime = TimeFilter.LAST_12_HOURS
                    binding.headerFilterTime.text =
                        resources.getQuantityString(R.plurals.filter_time_title, 12, 12)
                }
                R.id.filterTimeLast24Hours -> {
                    viewModel.filterTime = TimeFilter.LAST_24_HOURS
                    binding.headerFilterTime.text =
                        resources.getQuantityString(R.plurals.filter_time_title, 24, 24)
                }
                else -> {
                    viewModel.filterTime = TimeFilter.LAST_HOUR
                    binding.headerFilterTime.text =
                        resources.getQuantityString(R.plurals.filter_time_title, 6, 6)
                }
            }
            setupAmountsWidgets()
            viewModel.generateConsumptionsModel()
        }

    private fun setupChargerInfoWidget(charger: Charger) {
        binding.widgetInfoCharger.charger = charger
    }

    private fun setupAmountsWidgets() {
        binding.widgetAmountDischarged.amount = viewModel.generateAmountDischarged()
        binding.widgetAmountCharged.amount = viewModel.generateAmountCharged()
    }

    private fun setupConsumptionWidget(consumption: Consumption) {
        binding.widgetBuildingConsumption.consumptions = consumption
    }

    private fun setupLiveDataWidget(liveData: LiveData) {
        binding.widgetLiveData.liveData = liveData
    }
}