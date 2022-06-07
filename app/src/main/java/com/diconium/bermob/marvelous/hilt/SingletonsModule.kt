package com.diconium.bermob.marvelous.hilt

import com.diconium.bermob.marvelous.repository.MarvelRepository
import com.diconium.bermob.marvelous.service.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonsModule {

    @Singleton
    @Provides
    fun providesOkHttpClient() = OkHttpClient()

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .client(client)
        .baseUrl("https://marvel.com/api/v1/")
        .build()

    @Singleton
    @Provides
    fun providesMarvelService(retrofit: Retrofit) = retrofit.create(MarvelService::class.java)

    @Singleton
    @Provides
    fun providesMarvelRepository(service: MarvelService): MarvelRepository =
        MarvelRepository(service)

}
