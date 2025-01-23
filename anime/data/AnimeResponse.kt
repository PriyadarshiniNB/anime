package com.example.anime.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponse(
    @SerialName("data")
    val data: List<AnimeItem>
)


@Serializable
data class AnimeItem(
    @SerialName("mail_id")
    val mal_id: Int?,
    @SerialName("title")
    val title: String?,
    @SerialName("episodes")
    val episodes: Int?,
    @SerialName("score")
    val score: Double?,
    @SerialName("images")
    val images: AnimeImages?
)

@Serializable
data class AnimeDetailResponse(
    @SerialName("data") val data: AnimeDetailData
)

@Serializable
data class AnimeDetailData(
    @SerialName("title") val title: String?,
    @SerialName("synopsis") val synopsis: String?,
    @SerialName("genres") val genres: List<AnimeGenre>?,
    @SerialName("episodes") val episodes: Int?,
    @SerialName("score") val score: Double?,
    @SerialName("trailer") val trailer: AnimeTrailer,
    @SerialName("images") val images: AnimeImages
)


@Serializable
data class AnimeGenre(
    @SerialName("name") val name: String?
)

@Serializable
data class AnimeTrailer(
    @SerialName("youtube_id") val youtubeId: String?
)

@Serializable
data class AnimeImages(
    @SerialName("jpg") val jpg: AnimeImageJpg
)

@Serializable
data class AnimeImageJpg(
    @SerialName("image_url") val imageUrl: String?
)
