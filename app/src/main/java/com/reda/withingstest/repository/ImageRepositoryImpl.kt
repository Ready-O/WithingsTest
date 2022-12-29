package com.reda.withingstest.repository

import com.reda.withingstest.model.Image
import com.reda.withingstest.network.ImagesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imagesApi: ImagesApi
): ImageRepository {

    override suspend fun fetchImages(searchWord: String): Flow<Result<List<Image>>> = flow {
        try {
            val networkResponse = imagesApi.fetchImages(searchWord)
            emit(Result.success(networkResponse.hits))
        }
        catch(e: Exception){
            emit(Result.failure(e))
        }
    }
        .flowOn(Dispatchers.IO)
}