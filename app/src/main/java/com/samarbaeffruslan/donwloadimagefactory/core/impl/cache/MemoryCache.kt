package com.samarbaeffruslan.donwloadimagefactory.core.impl.cache

import android.graphics.Bitmap
import java.util.concurrent.ConcurrentHashMap

object MemoryCache {

    private val cache = ConcurrentHashMap<String, Bitmap>()

    operator fun set(url: String, bitmap: Bitmap) {
        cache[url] = bitmap
    }

    operator fun get(url: String): Bitmap? {
        return cache[url]
    }
}