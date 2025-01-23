package com.example.anime

import com.example.anime.data.AnimeRepository
import com.example.anime.viewmodel.AnimeDetailViewModel
import com.example.anime.viewmodel.AnimeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JikanApi::class.java)
    }

    single { AnimeRepository(get()) }

    viewModel { AnimeViewModel(get()) }
    viewModel { AnimeDetailViewModel(get()) }
}