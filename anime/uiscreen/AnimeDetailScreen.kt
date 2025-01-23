package com.example.anime.uiscreen

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.anime.data.AnimeDetailUIModel
import com.example.anime.viewmodel.AnimeDetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AnimeDetailScreen(
    animeId: Int,
    viewModel: AnimeDetailViewModel = getViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.fetchAnimeDetails(animeId)
    }

    val animeDetail by viewModel.animeDetail.collectAsState()
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
        animeDetail?.let { details ->
            AnimeDetailContent(details)
        }
    }
}

@SuppressLint("UnrememberedMutableState", "SetJavaScriptEnabled")
@Composable
fun AnimeDetailContent(details: AnimeDetailUIModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 16.dp, end = 16.dp)
    ) {

        details.title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Rating and Episodes
        Text(
            text = "Rating: ${details.score ?: "N/A"}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Episodes: ${details.episodes ?: "N/A"}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Genres
        Text(
            text = "Genres: ${details.genres}",
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Synopsis
        Text(
            text = "Synopsis:",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = details.synopsis ?: "N/A",
            style = MaterialTheme.typography.bodyMedium
        )

        if (details.trailer != null) {
            val url = "https://www.youtube.com/watch?v=${details.trailer}"

            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled = true
                        loadUrl(url)
                    }
                },
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
        }
        }

        Spacer(modifier = Modifier.height(16.dp))

    }





