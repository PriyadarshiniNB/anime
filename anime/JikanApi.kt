package com.example.anime


import com.example.anime.data.AnimeDetailResponse
import com.example.anime.data.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApi {
    @GET("v4/top/anime")
    suspend fun getAnime(): AnimeResponse

    @GET("v4/anime/{anime_id}")
    suspend fun getAnimeDetails(@Path("anime_id") animeId: Int): AnimeDetailResponse
}