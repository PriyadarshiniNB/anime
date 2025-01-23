package com.example.anime

import com.example.anime.data.AnimeDetailData
import com.example.anime.data.AnimeDetailUIModel
import com.example.anime.data.AnimeGenre
import com.example.anime.data.AnimeGenreUIModel
import com.example.anime.data.AnimeImageJpg
import com.example.anime.data.AnimeImageJpgUIModel
import com.example.anime.data.AnimeImages
import com.example.anime.data.AnimeImagesUIModel
import com.example.anime.data.AnimeItem
import com.example.anime.data.AnimeResponseModel
import com.example.anime.data.AnimeTrailer
import com.example.anime.data.AnimeTrailerUIModel
import com.example.anime.data.AnimeUIModel


fun List<AnimeItem>.toAnimeUIModel(): AnimeResponseModel {
    return AnimeResponseModel(
        animeUIModel = this.map { animeItem ->
            AnimeUIModel(
                mal_id = animeItem.mal_id,
                title = animeItem.title ?: "Unknown Title",
                episodes = animeItem.episodes?.toString() ?: "N/A",
                score = animeItem.score?.toString() ?: "N/A",
                images = animeItem.images?.toAnimeImages()
            )
        }
    )
}

fun AnimeDetailData.toAnimeDetailUIModel(): AnimeDetailUIModel {
    return AnimeDetailUIModel(
        title = title ?: "Unknown Title",
        synopsis = synopsis ?: "No synopsis available.",
        genres = genres?.map { it.toAnimeGenre() } ,
        episodes = episodes?.toString() ?: "N/A",
        score = score?.toString() ?: "N/A",
        trailer = trailer.toAnimeTrailer(),
        image_url = images.toAnimeImages()
    )
}

fun AnimeGenre.toAnimeGenre() =
     AnimeGenreUIModel(
        name = name
    )

fun AnimeTrailer.toAnimeTrailer() =
    AnimeTrailerUIModel(
        youtubeId = youtubeId
    )

fun AnimeImages.toAnimeImages() =
    AnimeImagesUIModel(
        jpg = jpg.toAnimeImageJpg()
    )

fun AnimeImageJpg.toAnimeImageJpg() =
    AnimeImageJpgUIModel(
        imageUrl = imageUrl
    )

