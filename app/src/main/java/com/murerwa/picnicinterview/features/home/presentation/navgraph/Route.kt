package com.murerwa.picnicinterview.features.home.presentation.navgraph

import android.net.Uri
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object HomeScreen: Route(route = "homeScreen")
    data object GifDetailsScreen: Route(
        route = "gifDetailsScreen",
        arguments = listOf(
            navArgument(GIF_IMAGE_KEY) {
                type = ParcelableParamType()
            }
        )
    ) {
        fun passGifImage(gifImage: GifImage): String {
            val json = Uri.encode(Gson().toJson(gifImage))
            return "gifDetailsScreen/$json"
        }
    }
}