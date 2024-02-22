package com.murerwa.picnicinterview.features.home.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.presentation.details.GifDetailsScreen
import com.murerwa.picnicinterview.features.home.presentation.home.HomeScreen

const val GIF_IMAGE_KEY = "gifImage"

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = Route.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Route.GifDetailsScreen.route + "/{$GIF_IMAGE_KEY}",
            arguments = Route.GifDetailsScreen.arguments
        ) {
            val gifImage = it.arguments?.getParcelable<GifImage>(GIF_IMAGE_KEY) ?: return@composable

            val gifId = ""

            GifDetailsScreen(
                gifImage = gifImage,
            ) {
                navController.popBackStack()
            }
        }
    }
}