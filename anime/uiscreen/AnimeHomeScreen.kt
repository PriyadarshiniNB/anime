package com.example.anime.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.anime.data.AnimeUIModel
import com.example.anime.viewmodel.AnimeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AnimeHomeScreen(
    navController: NavController,
    viewModel: AnimeViewModel = getViewModel()
) {
    val animeList by viewModel.animeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (!error.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Error: $error")
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(animeList.size) { index ->
                val anime = animeList[index]
                AnimeCard(anime) {
                    navController.navigate("DetailedScreen/${anime.mal_id}"){
                        launchSingleTop = true
                    }
                }
            }
        }
    }
}

@Composable
fun AnimeCard(anime: AnimeUIModel, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter =  rememberImagePainter(data = anime.images),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp).aspectRatio(1f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                anime.title?.let { Text(text = it, fontWeight = FontWeight.Bold) }
                Text(text = "Episodes: ${anime.episodes ?: "N/A"}")
                Text(text = "Rating: ${anime.score ?: "N/A"}")
            }
        }
    }
}