package com.murerwa.picnicinterview.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.features.home.presentation.home.HomeEvent
import com.murerwa.picnicinterview.features.home.presentation.home.HomeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    state: HomeState,
    event: (HomeEvent) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(end = 4.dp, bottom = 6.dp, start = 0.dp, top = 0.dp)
            .testTag(stringResource(R.string.tag_search_bar_component))
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state.isInSearchView) {
            Icon(
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                    .clickable {
                        event(HomeEvent.ToggleSearchView(false))
                        event(HomeEvent.UpdateSearchQuery(""))
                        focusManager.clearFocus()
                    }
                    .testTag(stringResource(R.string.tag_back_arrow))
                ,
                imageVector = Icons.Default.ArrowBack,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .clickable {
                    event(HomeEvent.ToggleSearchView(true))
                }
                .testTag(stringResource(R.string.tag_search_bar)),
            shape = RoundedCornerShape(12.dp),
            query = state.searchQuery,
            placeholder = {
                Text(
                    text = "Search"
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            active = false,
            onQueryChange = {
                event(HomeEvent.UpdateSearchQuery(it))
                if (it.length >= 2) {
                    event(HomeEvent.SearchGifs)
                }
            },
            onSearch =  { event(HomeEvent.SearchGifs) },
            content = {},
            onActiveChange = {
                event(HomeEvent.ToggleSearchView(it))
            },
            trailingIcon = {
                if (state.searchQuery.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Gray)
                            .padding(4.dp)
                            .clickable { event(HomeEvent.UpdateSearchQuery("")) }
                            .testTag(stringResource(R.string.tag_clear_icon))
                    ) {
                        Icon(
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp),
                            imageVector = Icons.Rounded.Clear,
                            tint = Color.White,
                            contentDescription = null,
                        )
                    }
                }
            },
        )
    }
}

@Preview
@Composable
fun SearchBarComponentPreview() {
    SearchBarComponent(
        state = HomeState(
            isInSearchView = false,
            searchQuery = ""
        ),
        event = {}
    )
}

@Preview
@Composable
fun SearchBarComponentPreviewWithArrow() {
    SearchBarComponent(
        state = HomeState(
            isInSearchView = true,
            searchQuery = ""
        ),
        event = {}
    )
}

@Preview
@Composable
fun SearchBarComponentPreviewWithClearIcon() {
    SearchBarComponent(
        state = HomeState(
            isInSearchView = true,
            searchQuery = "Dummy query"
        ),
        event = {}
    )
}