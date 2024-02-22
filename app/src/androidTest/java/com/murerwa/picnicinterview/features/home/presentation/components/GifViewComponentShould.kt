package com.murerwa.picnicinterview.features.home.presentation.components

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.core.extensions.capitalizeFirstLetter
import com.murerwa.picnicinterview.fixtures.tGifImageModel
import com.murerwa.picnicinterview.utils.BaseComposeTest
import org.junit.Before
import org.junit.Test
import java.util.Locale

class GifViewComponentShould: BaseComposeTest() {
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            GifViewComponent(
                gifImage = tGifImageModel.toGifImage()
            )
        }
    }

    @Test
    fun containAnImageComposableWithExpectedTraits() {
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_gif_image))
            .assertIsDisplayed()
    }

    @Test
    fun containATitleTextComposableWithExpectedTraits() {
        val expected = tGifImageModel.title.capitalizeFirstLetter(context)

        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_gif_title))
            .assertIsDisplayed()
            .assertTextContains(expected)
    }

    @Test
    fun containAUrlTextComposableWithExpectedTraits() {
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_gif_url))
            .assertIsDisplayed()
            .assertTextContains(tGifImageModel.bitlyUrl)
    }

    @Test
    fun containAnAgeRatingTextComposableWithExpectedTraits() {
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_gif_age_rating))
            .assertIsDisplayed()
            .assertTextContains(tGifImageModel.rating.uppercase(Locale.ROOT))
    }
}