package com.wallbox.ems.demo.data.charger

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Charger(
    val connected: Boolean,
    val name: String,
    val registrationDate: String,
    val icon: String,
    val bidirectional: Boolean,
    val bluetooth: Boolean,
    val wifi: Boolean
) : Parcelable