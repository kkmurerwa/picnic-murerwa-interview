package com.murerwa.picnicinterview.features.home.presentation.details

import android.content.Context
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.utils.BaseUITest
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsScreenAcceptanceTest: BaseUITest() {
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testClickingGifNavigatesToGifDetailsScreen() = runTest {
        val searchQuery = "funny"

        composeAndroidTestRule.onNodeWithText(context.getString(R.string.search))
            .performClick()
            .performTextInput(searchQuery)

        composeAndroidTestRule.waitUntil {
            composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_search_lazy_list))
                .isDisplayed()
        }

        composeAndroidTestRule.waitUntil(10000) {
            composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_search_lazy_list))
                .onChildAt(0)
                .isDisplayed()
        }

        composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_search_lazy_list))
            .onChildAt(0)
            .performClick()

        composeAndroidTestRule.waitUntil {
            composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_gif_component))
                .isDisplayed()
        }
        composeAndroidTestRule.onAllNodesWithTag(context.getString(R.string.tag_gif_title), true)
            .assertCountEquals(2)
        composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_gif_component))
            .assertIsDisplayed()
        composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_gif_image))
            .assertIsDisplayed()
        composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_gif_url))
            .assertIsDisplayed()
        composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_gif_age_rating))
            .assertIsDisplayed()
    }
}