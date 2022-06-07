package com.diconium.bermob.marvelous.repository

import com.diconium.bermob.marvelous.service.MarvelService
import com.diconium.bermob.marvelous.service.models.CharacterResponse

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/18 paging API
class MarvelRepository(private val service: MarvelService) {
    suspend fun getCharacters(query: String): Result<List<CharacterResponse>> {
        return try {
            Result.success(service.searchCharacters(query).data.results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
