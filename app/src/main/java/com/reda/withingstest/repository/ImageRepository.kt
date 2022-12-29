package com.reda.withingstest.repository

import com.reda.withingstest.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun fetchImages(searchWord: String): Flow<Result<List<Image>>>
}