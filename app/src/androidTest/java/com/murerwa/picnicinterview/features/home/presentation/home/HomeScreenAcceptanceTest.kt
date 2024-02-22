package com.murerwa.picnicinterview.features.home.presentation.home

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.requestFocus
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.utils.BaseUITest
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenAcceptanceTest: BaseUITest() {
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun testDefaultViewsAreDisplayed() {
        composeAndroidTestRule.onNodeWithText(context.getString(R.string.search))
            .assertIsDisplayed()
        composeAndroidTestRule.onNodeWithText(context.getString(R.string.random_selected_gif))
            .assertIsDisplayed()
    }

    @Test
    fun testClickingSearchBarOpensSearchScreen() = runTest {
        composeAndroidTestRule.onNodeWithText(context.getString(R.string.search))
            .assertIsDisplayed()
            .performClick()
            .requestFocus()
            .assertIsFocused()

        composeAndroidTestRule.waitUntil {
            composeAndroidTestRule.onNodeWithText(context.getString(R.string.search_results))
                .isDisplayed()
        }

        composeAndroidTestRule.onNodeWithText(context.getString(R.string.random_selected_gif))
            .assertIsNotDisplayed()
    }

    @Test
    fun testTypingSearchQueryDisplaysResults() = runTest {
        val searchQuery = "funny"

        composeAndroidTestRule.onNodeWithText(context.getString(R.string.search))
            .assertIsDisplayed()
            .performClick()
            .requestFocus()
            .assertIsFocused()
            .performTextInput(searchQuery)

        composeAndroidTestRule.waitUntil {
            composeAndroidTestRule.onNodeWithText(context.getString(R.string.search_results))
                .isDisplayed()
        }

        composeAndroidTestRule.onNodeWithText(searchQuery)
            .assertIsDisplayed()

        composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_search_list_component))
            .isDisplayed()
    }

    @Test
    fun testClickingGifNavigatesToGifDetailsScreen() = runTest {
        val searchQuery = "funny"

        composeAndroidTestRule.onNodeWithText(context.getString(R.string.search))
            .assertIsDisplayed()
            .performClick()
            .requestFocus()
            .assertIsFocused()
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
        composeAndroidTestRule.onNodeWithTag(context.getString(R.string.tag_gif_component))
            .assertIsDisplayed()
    }
}