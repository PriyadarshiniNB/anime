package com.example.anime.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime.data.AnimeDetailUIModel
import com.example.anime.data.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel(private val repository: AnimeRepository) : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeDetailUIModel?>(null)
    val animeDetail: StateFlow<AnimeDetailUIModel?> get() = _animeDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error


    fun fetchAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAnimeDetails(animeId)
                _animeDetail.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}