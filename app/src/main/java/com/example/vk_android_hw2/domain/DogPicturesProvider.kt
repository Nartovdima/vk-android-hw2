package com.example.vk_android_hw2.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_android_hw2.infra.DogsPicsApiFetchDto
import com.example.vk_android_hw2.infra.DogsPicsApiFetcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogPicturesProvider : ViewModel() {
    private val imagesUrls = MutableStateFlow<List<DogsPicsApiFetchDto>>(emptyList())
    val images: StateFlow<List<DogsPicsApiFetchDto>> = imagesUrls

    private val yetProcessing = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = yetProcessing

    private val fetchingError = MutableStateFlow<Exception?>(null)
    val error: StateFlow<Exception?> = fetchingError

    fun retrier() {
        fetchImages()
    }

    fun fetchImages() {
        viewModelScope.launch {
            yetProcessing.value = true
            fetchingError.value = null
            try {
                val response = DogsPicsApiFetcher.apiScheme.fetchImage()
                imagesUrls.value += response
            } catch (e: Exception) {
                fetchingError.value = e
            } finally {
                yetProcessing.value = false
            }
        }
    }


}