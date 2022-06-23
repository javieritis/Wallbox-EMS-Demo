package com.wallbox.ems.demo.widgets.chart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.wallbox.ems.demo.R
import com.wallbox.ems.demo.databinding.ViewItemMarkerChartBinding
import com.wallbox.ems.demo.library.extensions.TimeFormat
import com.wallbox.ems.demo.library.extensions.overrideColor
import com.wallbox.ems.demo.library.extensions.toDate
import com.wallbox.ems.demo.library.extensions.toString
import com.wallbox.ems.demo.screens.chart.MarkerData
import com.wallbox.ems.demo.screens.chart.ValueMarketData

class CustomMarkerView(context: Context?, layoutResource: Int) :
    MarkerView(context, layoutResource) {
    private val rootMarker = findViewById<View>(R.id.rootMarker)
    private val titleMarketChart = findViewById<TextView>(R.id.titleMarketChart)
    private val rvItemsChart = findViewById<RecyclerView>(R.id.rvItemsChart)

    init {
        rvItemsChart.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MarkerDataAdapter(listOf())
        }
    }

    override fun refreshContent(e: Entry, highlight: Highlight?) {
        if (e.data != null && e.data is MarkerData) {
            rootMarker.isVisible = true
            titleMarketChart.text =
                (e.data as MarkerData).timestamp.toDate(TimeFormat.RFC3339)?.toString(
                    TimeFormat.HH_MM
                )
            (rvItemsChart.adapter as MarkerDataAdapter).setValues((e.data as MarkerData).values)
        } else {
            rootMarker.isVisible = false
        }
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-width.toFloat(), -height.toFloat())
    }
}

class MarkerDataAdapter(
    private var values: List<ValueMarketData>
) :
    RecyclerView.Adapter<MarkerDataAdapter.MarkerDataViewHolder>() {

    class MarkerDataViewHolder(private val binding: ViewItemMarkerChartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(value: ValueMarketData) {
            binding.textItemMarketChart.text =
                "${value.title}: ${value.value} ${binding.root.context.getString(R.string.kwh)}"
            binding.pointItemMarketChart.background.overrideColor(value.color)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MarkerDataViewHolder {
        val binding =
            ViewItemMarkerChartBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return MarkerDataViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: MarkerDataViewHolder, position: Int) {
        viewHolder.bind(values[position])
    }

    override fun getItemCount() = values.size

    fun setValues(values: List<ValueMarketData>) {
        this.values = values
        notifyDataSetChanged()
    }
}