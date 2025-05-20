package com.samarbaeffruslan.donwloadimagefactory.core.presentation

import android.graphics.Bitmap

sealed interface ImageState {
    data object Loading : ImageState
    data class Loaded(val bitmap: Bitmap) : ImageState
    data object Error : ImageState
}