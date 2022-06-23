package com.wallbox.ems.demo.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wallbox.ems.demo.R
import com.wallbox.ems.demo.data.consumption.Consumption
import com.wallbox.ems.demo.data.consumption.SourceConsumption
import com.wallbox.ems.demo.databinding.IncludeViewSingleConsumptionBinding
import com.wallbox.ems.demo.databinding.ViewWidgetBuildingConsumptionBinding
import com.wallbox.ems.demo.library.extensions.drawableResourceId
import com.wallbox.ems.demo.library.extensions.roundTo
import kotlin.math.absoluteValue

class WidgetBuildingConsumption @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private var onClickWidgetListener: () -> Unit? = {  }

    private var binding: ViewWidgetBuildingConsumptionBinding

    var consumptions: Consumption? = null
        set(value) {
            field = value
            setDataInFields()
        }

    init {
        background = ColorDrawable(Color.TRANSPARENT)
        binding = ViewWidgetBuildingConsumptionBinding.inflate(LayoutInflater.from(context)).also {
            addView(it.root)
        }
        binding.rvConsumptions.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = ConsumptionAdapter(consumptions?.sourcesConsumptions ?: emptyList()) {
                onClickWidgetListener.invoke()
            }
        }
        setDataInFields()
    }

    private fun setDataInFields() {
        binding.buildingDemand.text = "${consumptions?.buildingConsumption?.roundTo(1) ?: "-"}"
        (binding.rvConsumptions.adapter as ConsumptionAdapter).setConsumptions(
            consumptions?.sourcesConsumptions ?: emptyList()
        )
    }

    fun onClickWidgetListener(onCheckedChangeListener: () -> Unit?) {
        this.onClickWidgetListener = onCheckedChangeListener
    }
}

class ConsumptionAdapter(
    private var consumptions: List<SourceConsumption>,
    private val onItemClickListener: () -> Unit
) :
    RecyclerView.Adapter<ConsumptionAdapter.ConsumptionViewHolder>() {

    class ConsumptionViewHolder(private val binding: IncludeViewSingleConsumptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(consumption: SourceConsumption, onItemClickListener: () -> Unit) {
            binding.root.setOnClickListener {
                onItemClickListener.invoke()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.progressbarConsumption.setProgress(
                    consumption.progress.absoluteValue.toInt(),
                    true
                )
            } else {
                binding.progressbarConsumption.progress = consumption.progress.absoluteValue.toInt()
            }
            binding.imageConsumption.apply {
                setImageResource(
                    context.drawableResourceId(consumption.icon) ?: R.drawable.ic_quasar
                )
            }
            binding.progressConsumption.text = "${consumption.progress.absoluteValue.roundTo(1)}%"
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ConsumptionViewHolder {
        val binding =
            IncludeViewSingleConsumptionBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        return ConsumptionViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ConsumptionViewHolder, position: Int) {
        viewHolder.bind(consumptions[position], onItemClickListener)
    }

    override fun getItemCount() = consumptions.size

    fun setConsumptions(consumptions: List<SourceConsumption>) {
        this.consumptions = consumptions
        notifyDataSetChanged()
    }
}