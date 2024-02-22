package com.murerwa.picnicinterview.features.home.presentation.components

import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.core.extensions.capitalizeFirstLetter
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage
import com.murerwa.picnicinterview.features.home.domain.entities.ImageUrls
import java.util.Locale

@Composable
fun GifViewComponent(
    gifImage: GifImage,
    onImageLoaded: () -> Unit = {}
) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .diskCachePolicy(CachePolicy.ENABLED)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    Column(
        modifier = Modifier
            .background(color = Color.Transparent)
            .padding(vertical = 8.dp)
            .testTag(stringResource(R.string.tag_gif_component))
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(MaterialTheme.shapes.medium)
                .testTag(stringResource(R.string.tag_gif_image)),
            model = ImageRequest.Builder(context)
                .data(gifImage.imageUrls.downsized).build(),
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            loading = {
                ImageProgressLoader()
            },
            onSuccess = {
                onImageLoaded()
            },
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.testTag(stringResource(R.string.tag_gif_title)),
                    text = gifImage.title.capitalizeFirstLetter(context),
                )
                Text(
                    modifier = Modifier.testTag(stringResource(R.string.tag_gif_url)),
                    text = gifImage.bitlyUrl
                )
            }
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                val fontSize = if (gifImage.rating.length >= 3) 14.sp else 22.sp

                Text(
                    modifier = Modifier.testTag(stringResource(R.string.tag_gif_age_rating)),
                    text = gifImage.rating.uppercase(Locale.ROOT),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = fontSize,
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GifViewComponentPreview() {
    GifViewComponent(
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
            bitlyUrl = "https://example.com/bitly_url",
            username = "user123"
        ),
    )
}
