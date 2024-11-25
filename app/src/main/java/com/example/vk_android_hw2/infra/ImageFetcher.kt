package com.example.vk_android_hw2.infra

import com.example.vk_android_hw2.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object  DogsPicsApiFetcher {
    private val BASE_URL = R.string.dogs_api_url

    val apiScheme: DogsPicsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogsPicsApi::class.java)
    }
}

interface DogsPicsApi {
    @GET("woof.json")
    suspend fun fetchImage(): DogsPicsApiFetchDto
}

data class DogsPicsApiFetchDto(
    val url: String,
    val id: Int
)
