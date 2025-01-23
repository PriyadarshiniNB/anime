package com.example.anime

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anime.ui.theme.AnimeTheme
import com.example.anime.uiscreen.AnimeDetailScreen
import com.example.anime.uiscreen.AnimeHomeScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AnimeApp()
                }
            }
        }
    }
}

@Composable
fun AnimeApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen"){ AnimeHomeScreen(navController) }
        composable("DetailedScreen/{animeId}") { backStackEntry ->
            val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull() ?: 0
            AnimeDetailScreen(animeId)
        }
    }
}