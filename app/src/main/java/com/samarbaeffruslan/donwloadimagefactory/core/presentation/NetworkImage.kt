package com.samarbaeffruslan.donwloadimagefactory.core.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.samarbaeffruslan.donwloadimagefactory.core.api.ImageLoader
import com.samarbaeffruslan.donwloadimagefactory.core.impl.loader.DefaultImageLoader

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    loader: ImageLoader<Bitmap> = DefaultImageLoader(),
    error: @Composable () -> Unit = { Text("Error") },
    loading: @Composable () -> Unit = { CircularProgressIndicator() }
) {
    var state by remember { mutableStateOf<ImageState>(ImageState.Loading) }

    LaunchedEffect(url) {
        state =loader.load(url)?.let { bitmapNotNull ->
            ImageState.Loaded(bitmapNotNull)
        } ?: ImageState.Error
    }

    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        when(state) {
            is ImageState.Loaded -> {
                Image(
                    bitmap = (state as ImageState.Loaded).bitmap.asImageBitmap(),
                    contentDescription = contentDescription,
                    modifier = modifier
                )
            }
            ImageState.Error -> error()
            ImageState.Loading -> loading()
        }
    }
}