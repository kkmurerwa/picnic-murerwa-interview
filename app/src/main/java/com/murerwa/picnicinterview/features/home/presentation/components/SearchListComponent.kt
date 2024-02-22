package com.murerwa.picnicinterview.features.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.core.res.Dimens.MediumPadding1
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.domain.entities.ImageUrls

@Composable
fun SearchListComponent(
    gifs: LazyPagingItems<GifImage>,
    onClick: (GifImage) -> Unit
) {
    Column(
        modifier = Modifier.testTag(stringResource(R.string.tag_search_list_component))
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize().testTag(stringResource(R.string.tag_search_lazy_list)),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(count = gifs.itemCount) { position ->
                gifs[position]?.let { gifImage ->
                    GifCard(gifImage = gifImage, onClick = { onClick(gifImage) })
                }
            }
        }
    }
}

@Composable
fun GifCard(
    modifier: Modifier = Modifier,
    gifImage: GifImage,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth(0.27f)
            .padding(horizontal = 4.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context)
                .diskCachePolicy(CachePolicy.ENABLED)
                .data(gifImage.imageUrls.originalStill).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            loading = {
                ImageProgressLoader(scale = 1.0f)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GifCardPreview() {
    GifCard(
        gifImage = GifImage(
            id = "123456",
            imageUrls = ImageUrls(
                downsized = "https://example.com/downsized_image.gif",
                downsizedSmall = "https://example.com/downsized_small_image.gif",
                looping = "https://example.com/looping_image.gif",
                original = "https://example.com/original_image.gif",
                originalStill = "https://example.com/original_still_image.jpg",
                preview = "https://example.com/preview_image.jpg",
                previewGif = "https://example.com/preview_gif_image.gif"
            ),
            rating = "PG",
            title = "Funny Gif",
            type = "gif",
            url = "https://example.com/gif_image.gif",
            bitlyUrl = "https://bit.ly/123456",
            username = "user123"
        ),
        onClick = {}
    )
}