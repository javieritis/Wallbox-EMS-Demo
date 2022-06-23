package com.wallbox.ems.demo.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton

class FilledRadioButton : AppCompatRadioButton {
    private var onCheckedButtonChangeListener: OnCheckedChangeListener? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setOwnOnCheckedChangeListener()
        buttonDrawable = null
    }

    fun setOnOnCheckedChangeListener(onCheckedChangeListener: OnCheckedChangeListener?) {
        this.onCheckedButtonChangeListener = onCheckedChangeListener
    }

    private fun setOwnOnCheckedChangeListener() {
        setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                onCheckedButtonChangeListener?.onCheckedChanged(buttonView, isChecked)
            }
        }
    }
}