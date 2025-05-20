package com.samarbaeffruslan.donwloadimagefactory.core.impl.http_client

import com.samarbaeffruslan.donwloadimagefactory.core.api.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class HttpURLConnection : HttpClient {

    override suspend fun getBytes(url: String): ByteArray? {
        return try {
            withContext(Dispatchers.IO) {
                val connection = (URL(url).openConnection() as? HttpURLConnection)?.apply {
                    connectTimeout = 5000
                    readTimeout = 5000
                    doInput = true
                    connect()
                } ?: return@withContext null

                with(connection) {
                    /* response code != 200 */
                    if (responseCode != HttpURLConnection.HTTP_OK) return@withContext null

                    if (contentLength <= 0) return@withContext null

                    val loadedBytes = BufferedInputStream(inputStream).readBytes()
                    /* check all read */
                    return@withContext if (loadedBytes.size != contentLength) {
                        null
                    } else {
                        loadedBytes
                    }
                }
            }
        } catch (_: Exception) {
            null
        }
    }

}