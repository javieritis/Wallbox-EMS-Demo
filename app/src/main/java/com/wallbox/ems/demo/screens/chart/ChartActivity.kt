package com.wallbox.ems.demo.screens.chart

import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.wallbox.ems.demo.R
import com.wallbox.ems.demo.data.historic_data.HistoricData
import com.wallbox.ems.demo.databinding.ActivityChartBinding
import com.wallbox.ems.demo.library.ActivityNavigator.Companion.EXTRA_FILTER_HOURS
import com.wallbox.ems.demo.library.ActivityNavigator.Companion.EXTRA_HISTORIC_DATA
import com.wallbox.ems.demo.library.base.BaseActivity
import com.wallbox.ems.demo.library.extensions.color
import com.wallbox.ems.demo.library.extensions.roundTo
import com.wallbox.ems.demo.screens.dashboard.TimeFilter
import com.wallbox.ems.demo.widgets.chart.CustomMarkerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.ExperimentalSerializationApi
import java.text.DateFormat
import java.util.*

data class MarkerData(
    val timestamp: String,
    val values: List<ValueMarketData>
)

data class ValueMarketData(
    val color: Int,
    val title: String,
    val value: Double
)

@ExperimentalSerializationApi
@AndroidEntryPoint
class ChartActivity : BaseActivity<ActivityChartBinding>() {
    override val binding by viewBinding(ActivityChartBinding::inflate)

    private val viewModel: ChartViewModel by viewModels()

    private val checkedChangeListener =
        CompoundButton.OnCheckedChangeListener { compoundButton: CompoundButton, _: Boolean ->
            when (compoundButton.id) {
                R.id.filterTimeLastHour -> {
                    viewModel.hours = TimeFilter.LAST_HOUR.hours
                }
                R.id.filterTimeLast6Hours -> {
                    viewModel.hours = TimeFilter.LAST_6_HOURS.hours
                }
                R.id.filterTimeLast12Hours -> {
                    viewModel.hours = TimeFilter.LAST_12_HOURS.hours
                }
                R.id.filterTimeLast24Hours -> {
                    viewModel.hours = TimeFilter.LAST_24_HOURS.hours
                }
                else -> {
                    viewModel.hours = TimeFilter.LAST_HOUR.hours
                }
            }
            viewModel.refreshData()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.consumption_chart)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel.onHistoricDataReceived(
            intent.getParcelableArrayListExtra<HistoricData>(EXTRA_HISTORIC_DATA)?.toList()
                ?: emptyList(), intent.getIntExtra(EXTRA_FILTER_HOURS, 1)
        )

        binding.containerButtonFilterTime.check(
            when (viewModel.hours) {
                TimeFilter.LAST_HOUR.hours -> R.id.filterTimeLastHour
                TimeFilter.LAST_6_HOURS.hours -> R.id.filterTimeLast6Hours
                TimeFilter.LAST_12_HOURS.hours -> R.id.filterTimeLast12Hours
                TimeFilter.LAST_24_HOURS.hours -> R.id.filterTimeLast24Hours
                else -> R.id.filterTimeLastHour
            }
        )

        viewModel.filteredHistoricData.observe(this) {
            setData(it)
        }

        binding.filterTimeLastHour.setOnOnCheckedChangeListener(checkedChangeListener)
        binding.filterTimeLast6Hours.setOnOnCheckedChangeListener(checkedChangeListener)
        binding.filterTimeLast12Hours.setOnOnCheckedChangeListener(checkedChangeListener)
        binding.filterTimeLast24Hours.setOnOnCheckedChangeListener(checkedChangeListener)

        binding.chart.setPinchZoom(false)
        binding.chart.isDoubleTapToZoomEnabled = false

        binding.chart.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "$value kWh"
            }
        }
        binding.chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
//        binding.chart.xAxis.valueFormatter = LineChartXAxisValueFormatter()
        binding.chart.xAxis.textColor = color(R.color.white)
        binding.chart.xAxis.isEnabled = false
        binding.chart.axisRight.isEnabled = false
        binding.chart.description.isEnabled = false
        binding.chart.axisLeft.textColor = color(R.color.white)

        binding.chart.legend.setDrawInside(false)
        binding.chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        binding.chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        binding.chart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        binding.chart.legend.isWordWrapEnabled = true
        binding.chart.legend.textColor = color(R.color.white)
        binding.chart.legend.textSize = 12f
        val mv = CustomMarkerView(this, R.layout.view_marker_chart)
        binding.chart.marker = mv

        binding.chart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {

            override fun onValueSelected(e: Entry?, h: Highlight?) {
                val highlight: Array<Highlight?> =
                    arrayOfNulls(binding.chart.data.dataSets.size)
                for (j in 0 until binding.chart.data.dataSets.size) {
                    val iDataSet: IDataSet<*> = binding.chart.data.dataSets[j]
                    for (i in (iDataSet as BarDataSet).values.indices) {
                        if (e != null) {
                            if (iDataSet.values[i].x == e.x) {
                                highlight[j] = Highlight(e.x, e.y, j)
                            }
                        }
                    }
                }
                binding.chart.highlightValues(highlight)
            }

            override fun onNothingSelected() {}
        })
    }

    //Temporally, axis x is disabled.
    class LineChartXAxisValueFormatter : IndexAxisValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            // Convert float value to date string
            // Convert from seconds back to milliseconds to format time  to show to the user
            // Convert float value to date string
            // Convert from seconds back to milliseconds to format time  to show to the user
            val emissionsMilliSince1970Time = value.toLong() * 1000

            // Show time in local version

            // Show time in local version
            val timeMilliseconds = Date(emissionsMilliSince1970Time)
            val dateTimeFormat: DateFormat =
                DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())

            return dateTimeFormat.format(timeMilliseconds)
        }
    }

    private fun setData(list: List<HistoricData>) {
        val values1: ArrayList<BarEntry> = ArrayList()
        val values2: ArrayList<BarEntry> = ArrayList()
        val values3: ArrayList<BarEntry> = ArrayList()
        val values4: ArrayList<BarEntry> = ArrayList()

        list.forEachIndexed { index, element ->
            val markerData = MarkerData(
                element.timestamp, listOf(
                    ValueMarketData(
                        this.color(R.color.green_500),
                        getString(R.string.charger),
                        element.quasarsActivePower.roundTo(1)
                    ),
                    ValueMarketData(
                        this.color(R.color.black_500),
                        getString(R.string.building_demand),
                        element.buildingActivePower.roundTo(1)
                    ),
                    ValueMarketData(
                        this.color(R.color.blue),
                        getString(R.string.grid_power),
                        element.gridActivePower.roundTo(1)
                    ),
                    ValueMarketData(
                        this.color(R.color.purple),
                        getString(R.string.solar_panel_power),
                        element.pvActivePower.roundTo(1)
                    )
                )
            )

            values1.add(
                BarEntry(
                    index.toFloat(),
//                    element.timestamp.toDate(TimeFormat.RFC3339)?.time?.toFloat() ?: 0f,
                    element.quasarsActivePower.roundTo(1).toFloat()
                )
            )
            values2.add(
                BarEntry(
                    index.toFloat(),
//                    element.timestamp.toDate(TimeFormat.RFC3339)?.time?.toFloat() ?: 0f,
                    element.buildingActivePower.roundTo(1).toFloat()
                )
            )
            values3.add(
                BarEntry(
                    index.toFloat(),
//                    element.timestamp.toDate(TimeFormat.RFC3339)?.time?.toFloat() ?: 0f,
                    element.gridActivePower.roundTo(1).toFloat()
                )
            )
            values4.add(
                BarEntry(
                    index.toFloat(),
//                    element.timestamp.toDate(TimeFormat.RFC3339)?.time?.toFloat() ?: 0f,
                    element.pvActivePower.roundTo(1).toFloat(),
                    markerData
                )
            )
        }

        val set1: BarDataSet
        val set2: BarDataSet
        val set3: BarDataSet
        val set4: BarDataSet

        if (binding.chart.data != null && binding.chart.data.dataSetCount > 0) {
            set1 = binding.chart.data.getDataSetByIndex(0) as BarDataSet
            set2 = binding.chart.data.getDataSetByIndex(1) as BarDataSet
            set3 = binding.chart.data.getDataSetByIndex(2) as BarDataSet
            set4 = binding.chart.data.getDataSetByIndex(3) as BarDataSet
            set1.values = values1
            set2.values = values2
            set3.values = values3
            set4.values = values4
            binding.chart.data.notifyDataChanged()
            binding.chart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values1, getString(R.string.charger))
            set1.color = this.color(R.color.green_500)
            set2 = BarDataSet(values2, getString(R.string.building_demand))
            set2.color = this.color(R.color.black_500)
            set3 = BarDataSet(values3, getString(R.string.grid_power))
            set3.color = this.color(R.color.blue)
            set4 = BarDataSet(values4, getString(R.string.solar_panel_power))
            set4.color = this.color(R.color.purple)

            val data = BarData(listOf(set1, set2, set3, set4))
            data.setValueTextSize(10f)
            binding.chart.data = data
        }
        binding.chart.invalidate()
    }
}
