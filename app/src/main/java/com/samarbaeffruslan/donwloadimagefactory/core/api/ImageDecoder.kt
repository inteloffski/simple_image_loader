package com.samarbaeffruslan.donwloadimagefactory.core.api

interface ImageDecoder<out T> {

    suspend fun decode(bytes: ByteArray): T
}