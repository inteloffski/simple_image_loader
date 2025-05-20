package com.samarbaeffruslan.donwloadimagefactory

import android.graphics.Bitmap

sealed interface ImageState {
    data object Loading : ImageState
    data class Loaded(val bitmap: Bitmap) : ImageState
    data object Error : ImageState
}