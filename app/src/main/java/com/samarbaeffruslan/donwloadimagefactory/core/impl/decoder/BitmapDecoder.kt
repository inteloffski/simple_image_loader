package com.samarbaeffruslan.donwloadimagefactory.core.impl.decoder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.samarbaeffruslan.donwloadimagefactory.core.api.ImageDecoder

class BitmapDecoder: ImageDecoder<Bitmap> {
    override suspend fun decode(bytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}