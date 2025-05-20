package com.samarbaeffruslan.donwloadimagefactory

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.launch

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    placeholder: @Composable () -> Unit = { CircularProgressIndicator() }
) {
    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(url) {
        scope.launch {
            ImageLoader.load(url)
                ?.asImageBitmap()
                ?.also { bitmap = it }
        }
    }

    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        bitmap?.let { bitmapNotNull ->
            Image(
                bitmap = bitmapNotNull,
                contentDescription = contentDescription,
                modifier = modifier
            )
        } ?: placeholder()
    }
}