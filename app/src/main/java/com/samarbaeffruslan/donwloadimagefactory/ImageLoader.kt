package com.samarbaeffruslan.donwloadimagefactory

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

object ImageLoader {

    suspend fun load(url: String): Bitmap? {
        return try {
            withContext(Dispatchers.IO) {
                MemoryCache[url]?.let { bitmap -> return@withContext bitmap }

                val connection = (URL(url).openConnection() as? HttpURLConnection)?.apply {
                    connectTimeout = 5000
                    readTimeout = 5000
                    doInput = true
                    connect()
                } ?: return@withContext null

                if(connection.responseCode != HttpURLConnection.HTTP_OK) return@withContext null

                connection.inputStream.use { inputStream ->
                    val buffer = BufferedInputStream(inputStream)
                    val bitmap = BitmapFactory.decodeStream(buffer)
                    MemoryCache[url] = bitmap
                    bitmap
                }
            }
        } catch (_: Exception) {
            null
        }
    }
}