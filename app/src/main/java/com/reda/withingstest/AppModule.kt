package com.reda.withingstest

import com.reda.withingstest.network.ImagesApi
import com.reda.withingstest.repository.ImageRepository
import com.reda.withingstest.repository.ImageRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindsImageRepository(
        imageRepository: ImageRepositoryImpl
    ): ImageRepository

    companion object {
        @Provides
        @Singleton
        fun providesMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Provides
        @Singleton
        fun providesNewsApi(): ImagesApi {
            return Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(MoshiConverterFactory.create(providesMoshi()))
                .build()
                .create(ImagesApi::class.java)
        }
    }
}