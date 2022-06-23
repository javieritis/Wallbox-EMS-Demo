package com.wallbox.ems.demo.domain.live_data

import android.content.Context
import com.wallbox.ems.demo.data.live_data.LiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.ByteArrayInputStream
import javax.inject.Inject

const val FILE_JSON_LIVE_DATA = "live_data.json"

@OptIn(ExperimentalSerializationApi::class)
class LocalLiveDataRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) : LiveDataRepository {

    override suspend fun fetchLiveData(): LiveData? {
        runCatching {
            context.assets.open(FILE_JSON_LIVE_DATA).bufferedReader().use { it.readText() }
        }.onSuccess {
            return Json.decodeFromStream(ByteArrayInputStream(it.toByteArray()))
        }
        return null
    }
}