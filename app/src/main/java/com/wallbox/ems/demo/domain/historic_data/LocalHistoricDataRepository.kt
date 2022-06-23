package com.wallbox.ems.demo.domain.historic_data

import android.content.Context
import com.wallbox.ems.demo.data.historic_data.HistoricData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.ByteArrayInputStream
import javax.inject.Inject

const val FILE_JSON_HISTORIC_DATA = "historic_data.json"

class LocalHistoricDataRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) : HistoricDataRepository {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun fetchHistoricData(): List<HistoricData> {
        runCatching {
            context.assets.open(FILE_JSON_HISTORIC_DATA).bufferedReader().use { it.readText() }
        }.onSuccess { result ->
            val list =
                Json.decodeFromStream<List<HistoricData>>(ByteArrayInputStream(result.toByteArray()))
            return list.sortedByDescending { it.timestamp }
        }
        return emptyList()
    }
}