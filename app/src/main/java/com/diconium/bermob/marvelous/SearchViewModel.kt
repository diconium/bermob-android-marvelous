package com.diconium.bermob.marvelous

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diconium.bermob.marvelous.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/14 tests
@HiltViewModel
class SearchViewModel @Inject constructor(
    // private val query: String,
    private val repo: MarvelRepository,
) : ViewModel() {


    // TODO: https://github.com/diconium/bermob-android-marvelous/issues/15 Flows
    private var items = mutableListOf<TempData>()
    private val _data = MutableLiveData<List<TempData>>()
    val data: LiveData<List<TempData>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO) { loadMore() }
    }

    private suspend fun loadMore() {
        delay(1300)
        items.addAll(
            listOf(
                TempData("url", "IronMan", "Has he lost his mind, can he see or is he blind?"),
                TempData("url", "WarMachine", "Like iron man, but not such a cool red"),
                TempData("url", "Thor", "Something something norwegian"),
                TempData("url", "SpiderMan", "Started doing selfies before everyone else"),
            )
        )
        withContext(Dispatchers.Main) { _data.value = items.toList() }
    }

}


data class TempData(val u: String, val t: String, val s: String)
