package com.example.anime.data

data class AnimeResponseModel(
    var animeUIModel : List<AnimeUIModel>
)

data class AnimeUIModel(
    val mal_id: Int?,
    val title: String? = "Unknown Title",
    val episodes: String?,
    val score: String?,
    val images: AnimeImagesUIModel?
)

data class AnimeDetailUIModel(
    val title: String?,
    val synopsis: String?,
    val genres: List<AnimeGenreUIModel>?,
    val episodes: String?,
    val score: String?,
    val trailer: AnimeTrailerUIModel,
    val image_url: AnimeImagesUIModel
)


data class AnimeGenreUIModel(
   val name: String?
)

data class AnimeTrailerUIModel(
     val youtubeId: String?
)

data class AnimeImagesUIModel(
    val jpg: AnimeImageJpgUIModel
)

data class AnimeImageJpgUIModel(
    val imageUrl: String?
)