package com.wallbox.ems.demo.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.wallbox.ems.demo.R
import com.wallbox.ems.demo.databinding.ViewWidgetInfoAmountEnergyBinding
import kotlin.math.absoluteValue

enum class AmountType(val value: Int) {
    DISCHARGED(0),
    CHARGED(1);

    companion object {
        fun fromParams(id: Int): AmountType {
            return when (id) {
                0 -> DISCHARGED
                1 -> CHARGED
                else -> throw IllegalAccessException("Unsupported AmountType")
            }
        }
    }
}

class WidgetInfoAmountEnergy @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding: ViewWidgetInfoAmountEnergyBinding

    var amountType: AmountType
    var amount: Double? = 0.0
        set(value) {
            field = value
            setDataInFields()
        }

    init {
        background = ColorDrawable(Color.TRANSPARENT)
        binding = ViewWidgetInfoAmountEnergyBinding.inflate(LayoutInflater.from(context)).also {
            addView(it.root)
        }

        val typedArray: TypedArray = getContext().obtainStyledAttributes(
            attrs,
            R.styleable.WidgetInfoAmountEnergy,
            defStyleAttr,
            0
        )
        amountType = AmountType.fromParams(
            typedArray.getInt(
                R.styleable.WidgetInfoAmountEnergy_amountType,
                AmountType.DISCHARGED.value
            )
        )
        typedArray.recycle()
        binding.titleInfoAmount.text =
            if (amountType == AmountType.DISCHARGED) context.getString(R.string.energy_discharged) else context.getString(
                R.string.energy_charged
            )
        setDataInFields()
    }

    private fun setDataInFields() {
        binding.descriptionInfoAmount.text = "${amount?.absoluteValue ?: "-"}"
    }
}