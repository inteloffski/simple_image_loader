package com.samarbaeffruslan.donwloadimagefactory.core.impl.loader

import android.graphics.Bitmap
import com.samarbaeffruslan.donwloadimagefactory.core.impl.cache.MemoryCache
import com.samarbaeffruslan.donwloadimagefactory.core.api.HttpClient
import com.samarbaeffruslan.donwloadimagefactory.core.api.ImageDecoder
import com.samarbaeffruslan.donwloadimagefactory.core.api.ImageLoader
import com.samarbaeffruslan.donwloadimagefactory.core.impl.decoder.BitmapDecoder
import com.samarbaeffruslan.donwloadimagefactory.core.impl.http_client.HttpURLConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultImageLoader(
    private val decoder: ImageDecoder<Bitmap> = BitmapDecoder(),
    private val httpClientEngine: HttpClient = HttpURLConnection()
) : ImageLoader<Bitmap> {

    override suspend fun load(url: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            MemoryCache[url]?.let { bitmapFromCache -> return@withContext bitmapFromCache }

            httpClientEngine.getBytes(url)?.let { bytes ->
                val bitmap = decoder.decode(bytes)
                MemoryCache[url] = bitmap
                bitmap
            }
        }
    }
}