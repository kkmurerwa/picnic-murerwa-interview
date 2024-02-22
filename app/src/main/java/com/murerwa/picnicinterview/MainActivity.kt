package com.murerwa.picnicinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.murerwa.picnicinterview.features.home.presentation.navgraph.NavGraph
import com.murerwa.picnicinterview.features.home.presentation.navgraph.Route
import com.murerwa.picnicinterview.core.ui.theme.PicnicmurerwainterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicnicmurerwainterviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(startDestination = Route.HomeScreen.route)
                }
            }
        }
    }
}