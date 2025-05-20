package com.samarbaeffruslan.donwloadimagefactory

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL

object ImageLoader {

    suspend fun load(url: String): Bitmap? {
        return try {
            withContext(Dispatchers.IO) {
                MemoryCache[url]?.let { return@withContext it }

                /* set connection */
                val connection = (URL(url).openConnection() as? HttpURLConnection)?.apply {
                    connectTimeout = 5000
                    readTimeout = 5000
                    doInput = true
                    connect()
                } ?: return@withContext null

                /* response code != 200 */
                if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                    return@withContext null
                }

                val contentLength = connection.contentLength

                if (contentLength <= 0) return@withContext null

                /* download to memory */
                val inputStream = BufferedInputStream(connection.inputStream)
                val output = ByteArrayOutputStream()

                val buffer = ByteArray(8 * 1024)
                var totalRead = 0
                var read: Int
                while (inputStream.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                    totalRead += read
                }

                val imageData = output.toByteArray()

                /* check all read */
                if (totalRead != contentLength) {
                    return@withContext null
                }

                /* check header */
                val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
                BitmapFactory.decodeByteArray(imageData, 0, imageData.size, options)

                if (options.outWidth <= 0 || options.outHeight <= 0) {
                    return@withContext null
                }

                /* decoder */
                val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
                    ?: return@withContext null

                /* save to cache */
                MemoryCache[url] = bitmap
                bitmap
            }
        } catch (_: Exception) {
            null
        }
    }
}