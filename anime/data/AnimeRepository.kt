package com.example.anime.data

import com.example.anime.JikanApi
import com.example.anime.toAnimeDetailUIModel
import com.example.anime.toAnimeUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepository (private val api : JikanApi){


    suspend fun getAnime(): AnimeResponseModel = withContext(Dispatchers.IO) {
        val response = api.getAnime()
        println("response api + $response")
        response.data.toAnimeUIModel()
    }

    suspend fun getAnimeDetails(animeId: Int): AnimeDetailUIModel = withContext(Dispatchers.IO) {
        val response = api.getAnimeDetails(animeId)
        println("response detail api + $response")
        response.data.toAnimeDetailUIModel()
    }

}