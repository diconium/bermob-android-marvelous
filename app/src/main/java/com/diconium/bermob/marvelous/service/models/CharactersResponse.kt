// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json               = Json(JsonConfiguration.Stable)
// val charactersResponse = json.parse(CharactersResponse.serializer(), jsonString)

package com.diconium.bermob.marvelous.service.models

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: CharactersDataResponse,
    val etag: String
)

@Serializable
data class CharactersDataResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterResponse>
)

@Serializable
data class CharacterResponse(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<URLResponse>,
    val thumbnail: ThumbnailResponse,
    val comics: ComicsResponse,
    val stories: StoriesResponse,
    val events: ComicsResponse,
    val series: ComicsResponse
)

@Serializable
data class ComicsResponse(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<ComicsItemResponse>
)

@Serializable
data class ComicsItemResponse(
    val resourceURI: String,
    val name: String
)

@Serializable
data class StoriesResponse(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<StoriesItemResponse>
)

@Serializable
data class StoriesItemResponse(
    val resourceURI: String,
    val name: String,
    val type: String
)

@Serializable
data class ThumbnailResponse(
    val path: String,
    val extension: String
)

@Serializable
data class URLResponse(
    val type: String,
    val url: String
)
