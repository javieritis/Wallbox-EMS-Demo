package com.wallbox.ems.demo.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.wallbox.ems.demo.R
import com.wallbox.ems.demo.data.charger.Charger
import com.wallbox.ems.demo.databinding.ViewWidgetInfoChargerBinding
import com.wallbox.ems.demo.library.extensions.drawableResourceId

class WidgetInfoCharger @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var binding: ViewWidgetInfoChargerBinding

    var charger: Charger? = null
        set(value) {
            field = value
            setDataInFields()
        }

    init {
        background = ColorDrawable(Color.TRANSPARENT)
        binding = ViewWidgetInfoChargerBinding.inflate(LayoutInflater.from(context)).also {
            addView(it.root)
        }
        setDataInFields()
    }

    private fun setDataInFields() {
        binding.titleCharger
        charger?.let {
            binding.titleCharger.text = it.name
            binding.descriptionCharger.text =
                if (it.connected) context.getString(R.string.connected) else context.getString(
                    R.string.disconnected
                )
            binding.icon.setImageResource(
                context.drawableResourceId(it.icon) ?: R.drawable.ic_quasar
            )
            binding.iconBidirectional.isVisible = it.bidirectional
            binding.iconBt.isVisible = it.bluetooth
            binding.iconWifi.isVisible = it.wifi
        }
    }
}