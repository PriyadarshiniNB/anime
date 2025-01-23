package com.example.anime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.data.AnimeRepository
import com.example.anime.data.AnimeUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeViewModel(private val repository: AnimeRepository) : ViewModel() {

    private val _animeList = MutableStateFlow<List<AnimeUIModel>>(emptyList())
    val animeList: StateFlow<List<AnimeUIModel>> get() = _animeList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    init {
        fetchTopAnime()
    }

    private fun fetchTopAnime() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAnime()
                _animeList.value = response.animeUIModel
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}