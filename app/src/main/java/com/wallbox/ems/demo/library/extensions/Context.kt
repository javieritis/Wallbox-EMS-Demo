package com.wallbox.ems.demo.library.extensions

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.wallbox.ems.demo.R

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(@StringRes res: Int) {
    Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
}

fun Context.drawableResourceId(name: String): Int? {
    val id = resources.getIdentifier(name, "drawable", packageName)
    return if (id > 0) id else null
}

fun Context.color(@ColorRes resourceId: Int): Int = ContextCompat.getColor(this, resourceId)

fun Context.color(name: String): Int {
    return try {
        ContextCompat.getColor(this, resources.getIdentifier(name, "color", packageName))
    } catch (e: Resources.NotFoundException) {
        R.color.green_500
    }
}