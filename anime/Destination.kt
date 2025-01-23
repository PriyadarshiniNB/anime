package com.example.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {

    @Serializable
    @SerialName("HomeScreen")
    data object HomeScreen : Destination

    @Serializable
    @SerialName("DetailedScreen")
    data class DetailedScreen(val animeId : Int) : Destination
}