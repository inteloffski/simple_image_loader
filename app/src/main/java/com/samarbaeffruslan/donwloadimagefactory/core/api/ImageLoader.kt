package com.samarbaeffruslan.donwloadimagefactory.core.api

interface ImageLoader<out T> {

    suspend fun load(url: String): T?
}