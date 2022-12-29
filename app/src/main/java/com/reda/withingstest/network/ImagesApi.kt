package com.reda.withingstest.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {
    @GET("api")
    suspend fun fetchImages(
        @Query("q") word: String,
        @Query("key") key: String = API_KEY,
        @Query("image_type") imageType: String = IMAGE_TYPE
    ): ApiResponse

    companion object{
        private const val API_KEY = "18021445-326cf5bcd3658777a9d22df6f "
        private const val IMAGE_TYPE = "photo"
    }
}