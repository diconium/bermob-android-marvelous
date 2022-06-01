package com.diconium.bermob.marvelous

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diconium.bermob.marvelous.service.MarvelService
import com.diconium.bermob.marvelous.service.models.CharacterResponse
import com.diconium.bermob.marvelous.service.models.ComicsResponse
import com.diconium.bermob.marvelous.service.models.StoriesResponse
import com.diconium.bermob.marvelous.service.models.ThumbnailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/14 tests
class SearchViewModel(
    private val query: String,
    private val service: MarvelService,
) : ViewModel() {

    // TODO: https://github.com/diconium/bermob-android-marvelous/issues/15 Flows
    private var items = mutableListOf<CharacterResponse>()
    private val _data = MutableLiveData<List<CharacterResponse>>()
    val data: LiveData<List<CharacterResponse>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO) { loadMore() }
    }

    private suspend fun loadMore() {
        delay(1300)
        items.addAll(
            listOf(
                dummyCharacter(0, "IronMan", "Has he lost his mind, can he see or is he blind?"),
                dummyCharacter(1, "WarMachine", "Like iron man, but not such a cool red"),
                dummyCharacter(2, "Thor", "Something something norwegian"),
                dummyCharacter(3, "SpiderMan", "Started doing selfies before everyone else"),
            )
        )
        withContext(Dispatchers.Main) { _data.value = items.toList() }
    }

    private fun dummyCharacter(id: Int, name: String, description: String): CharacterResponse {
        return CharacterResponse(
            id,
            name,
            description,
            "",
            "",
            emptyList(),
            ThumbnailResponse("", ""),
            ComicsResponse(0, 0, "", emptyList()),
            StoriesResponse(0, 0, "", emptyList()),
            ComicsResponse(0, 0, "", emptyList()),
            ComicsResponse(0, 0, "", emptyList()),
        )
    }

}


data class TempData(val u: String, val t: String, val s: String)
