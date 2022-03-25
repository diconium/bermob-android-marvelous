package com.diconium.bermob.marvelous.service

import com.diconium.bermob.marvelous.service.models.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api to access the marvel backend
 * https://developer.marvel.com/docs
 */
interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun searchCharacters(@Query("name") name: String): CharactersResponse

}
