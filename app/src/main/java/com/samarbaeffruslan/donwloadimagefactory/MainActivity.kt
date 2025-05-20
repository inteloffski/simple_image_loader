package com.samarbaeffruslan.donwloadimagefactory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.samarbaeffruslan.donwloadimagefactory.core.presentation.NetworkImage
import com.samarbaeffruslan.donwloadimagefactory.ui.theme.DonwloadImageFactoryTheme

const val IMAGE_URL = "https://img.goodfon.ru/original/2880x1800/2/f9/lisa-zima-sneg-8.jpg"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DonwloadImageFactoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NetworkImage(
                        modifier = Modifier.padding(innerPadding),
                        url = IMAGE_URL
                    )
                }
            }
        }
    }
}
