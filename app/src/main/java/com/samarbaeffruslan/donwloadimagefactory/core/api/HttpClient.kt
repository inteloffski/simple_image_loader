package com.samarbaeffruslan.donwloadimagefactory.core.api

interface HttpClient {
    suspend fun getBytes(url: String): ByteArray?
}