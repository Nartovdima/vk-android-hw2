package com.example.vk_android_hw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import coil.ImageLoader
import coil.disk.DiskCache
import coil.request.CachePolicy
import com.example.vk_android_hw2.domain.DogPicturesProvider
import com.example.vk_android_hw2.ui.DogPictureSheetsGrid

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<DogPicturesProvider>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogPictureSheetsGrid(viewModel = viewModel, imageLoader = ImageLoader.Builder(this)
                .diskCache {
                    DiskCache.Builder()
                        .directory(cacheDir.resolve(getString(R.string.images_local_storage)))
                        .maxSizePercent(0.5)
                        .build()
                }
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build())
        }
    }
}