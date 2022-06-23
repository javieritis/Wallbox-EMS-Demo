package com.wallbox.ems.demo.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.wallbox.ems.demo.R
import com.wallbox.ems.demo.data.live_data.LiveData
import com.wallbox.ems.demo.databinding.IncludeViewItemLiveDataBinding
import com.wallbox.ems.demo.databinding.ViewWidgetLiveDataBinding
import com.wallbox.ems.demo.library.extensions.roundTo

class WidgetLiveData @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var binding: ViewWidgetLiveDataBinding

    var liveData: LiveData? = null
        set(value) {
            field = value
            setDataInFields()
        }

    init {
        background = ColorDrawable(Color.TRANSPARENT)
        binding = ViewWidgetLiveDataBinding.inflate(LayoutInflater.from(context)).also {
            addView(it.root)
        }
        setDataInFields()
    }

    private fun setDataInFields() {
        liveData?.let { liveData ->
            binding.containerLiveData.apply {
                val carBinding =
                    IncludeViewItemLiveDataBinding.inflate(LayoutInflater.from(context)).also {
                        it.titleConsumptionLiveData.text = context.getString(R.string.battery)
                        it.valueDemand.text = "${liveData.systemSoc.roundTo(1)}"
                        it.iconConsumptionLiveData.setImageResource(R.drawable.ic_ev)
                        it.suffix.text = "%"
                    }
                addView(carBinding.root)

                val buildingBinding =
                    IncludeViewItemLiveDataBinding.inflate(LayoutInflater.from(context)).also {
                        it.titleConsumptionLiveData.text =
                            context.getString(R.string.building_demand)
                        it.valueDemand.text = "${liveData.buildingDemand.roundTo(1)}"
                        it.iconConsumptionLiveData.setImageResource(R.drawable.ic_house)
                        it.suffix.text =
                            context.getString(R.string.kwh)
                    }
                addView(buildingBinding.root)

                val pvBinding =
                    IncludeViewItemLiveDataBinding.inflate(LayoutInflater.from(context)).also {
                        it.titleConsumptionLiveData.text =
                            context.getString(R.string.solar_panel_power)
                        it.valueDemand.text = "${liveData.solarPower.roundTo(1)}"
                        it.iconConsumptionLiveData.setImageResource(R.drawable.ic_pv)
                        it.suffix.text =
                            context.getString(R.string.kwh)
                    }
                addView(pvBinding.root)

                val gridBinding =
                    IncludeViewItemLiveDataBinding.inflate(LayoutInflater.from(context)).also {
                        it.titleConsumptionLiveData.text = context.getString(R.string.grid_power)
                        it.valueDemand.text = "${liveData.gridPower.roundTo(1)}"
                        it.iconConsumptionLiveData.setImageResource(R.drawable.ic_plug)
                        it.suffix.text =
                            context.getString(R.string.kwh)
                    }
                addView(gridBinding.root)

                val chargerBinding =
                    IncludeViewItemLiveDataBinding.inflate(LayoutInflater.from(context)).also {
                        it.titleConsumptionLiveData.text =
                            if (liveData.quasarsPower < 0)
                                context.getString(R.string.card_being_discharged)
                            else context.getString(
                                R.string.card_being_charged
                            )
                        it.valueDemand.text = "${liveData.quasarsPower.roundTo(1)}"
                        it.iconConsumptionLiveData.setImageResource(R.drawable.ic_quasar)
                        it.suffix.text =
                            context.getString(R.string.kwh)
                    }
                addView(chargerBinding.root)
            }
        }
    }
}