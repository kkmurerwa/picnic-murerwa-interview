package com.murerwa.picnicinterview.features.home.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.features.home.presentation.components.EmptyResultComponent
import com.murerwa.picnicinterview.features.home.presentation.components.GifViewComponent
import com.murerwa.picnicinterview.features.home.presentation.components.SearchBarComponent
import com.murerwa.picnicinterview.features.home.presentation.components.SearchListComponent
import com.murerwa.picnicinterview.features.home.presentation.navgraph.Route

const val HOME_SCREEN_LAUNCHED_EFFECT_KEY = "home-screen-launched-effect-key"

@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: HomeScreenViewModel = hiltViewModel()
    val state = viewModel.state

    LaunchedEffect(key1 = HOME_SCREEN_LAUNCHED_EFFECT_KEY) {
        if (!state.value.isInSearchView) {
            viewModel.onEvent(event = HomeEvent.GetRandomGif)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SearchBarComponent(
            state = state.value,
            event = viewModel::onEvent
        )
        Spacer(modifier = Modifier.padding(8.dp))

        if (state.value.isInSearchView) {
            Column {
                Text(text = stringResource(R.string.search_results))
                Spacer(modifier = Modifier.padding(8.dp))
                when (val uiState = state.value.gifs) {
                    null -> {}
                    else -> {
                        val gifs = uiState.collectAsLazyPagingItems()

                        SearchListComponent(
                            gifs = gifs,
                            onClick = { gifImage ->
                                navController.navigate(
                                    route = Route.GifDetailsScreen.passGifImage(gifImage)
                                )
                            }
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.random_selected_gif)
                )
                Spacer(modifier = Modifier.height(4.dp))
                when (val gifImage = state.value.randomGif) {
                    null -> {
                        if (state.value.loadingError.isNotEmpty()) {
                            EmptyResultComponent(message = stringResource(R.string.no_gif_found))
                        }
                    }
                    else -> {
                        GifViewComponent(
                            gifImage = gifImage,
                            onImageLoaded = {
                                viewModel.onEvent(event = HomeEvent.StartRandomGifRefreshTimer)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}