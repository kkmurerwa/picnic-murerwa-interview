package com.murerwa.picnicinterview.features.home.presentation.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.core.extensions.capitalizeFirstLetter
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.domain.entities.ImageUrls
import com.murerwa.picnicinterview.features.home.presentation.components.GifViewComponent

@Composable
fun GifDetailsScreen(
    gifImage: GifImage,
    onBackPress: () -> Unit
) {
    val context = LocalContext.current

    // Trigger vm call

    Column(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp, start = 4.dp, top = 4.dp, bottom = 4.dp)
                    .clickable { onBackPress() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier.testTag(stringResource(id = R.string.tag_gif_title)),
                text = gifImage.title.capitalizeFirstLetter(context),
                style = TextStyle(
                    fontSize = 18.sp,
                ),
            )
        }
        Box {
            GifViewComponent(gifImage = gifImage)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GifDetailsScreenPreview() {
    GifDetailsScreen(
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
    ) {}
}