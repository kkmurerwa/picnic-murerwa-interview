package com.murerwa.picnicinterview.features.home.presentation.components

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.features.home.presentation.home.HomeState
import com.murerwa.picnicinterview.utils.BaseComposeTest
import org.junit.Test

class SearchBarComponentShould: BaseComposeTest() {
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun searchBarShouldBeVisibleWhenNotInSearchView() {
        setUpDefaultSearchBarComponent()

        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_search_bar))
            .assertIsDisplayed()

        // Back arrow should not be displayed
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_back_arrow))
            .assertIsNotDisplayed()

        // Clear icon should not be displayed
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_clear_icon))
            .assertIsNotDisplayed()
    }

    @Test
    fun searchBarShouldBeVisibleWhenInSearchViewWithSearchQuery() {
        setUpSearchBarComponentInSearchViewWithSearchQuery()

        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_search_bar))
            .assertIsDisplayed()

        // Back arrow should be displayed
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_back_arrow))
            .assertIsDisplayed()

        // Clear icon should be displayed
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_clear_icon))
            .assertIsDisplayed()
    }

    @Test
    fun searchBarShouldBeVisibleWhenInSearchViewWithoutSearchQuery() {
        setUpSearchBarComponentInSearchViewWithoutSearchQuery()

        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_search_bar))
            .assertIsDisplayed()

        // Back arrow should be displayed
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_back_arrow))
            .assertIsDisplayed()

        // Clear icon should not be displayed
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_clear_icon))
            .assertIsNotDisplayed()
    }


    private fun setUpDefaultSearchBarComponent() {
        composeTestRule.setContent {
            SearchBarComponent(
                state = HomeState(
                    isInSearchView = false,
                    searchQuery = ""
                ),
                event = {}
            )
        }
    }

    private fun setUpSearchBarComponentInSearchViewWithSearchQuery() {
        composeTestRule.setContent {
            SearchBarComponent(
                state = HomeState(
                    isInSearchView = true,
                    searchQuery = "search query"
                ),
                event = {}
            )
        }
    }

    private fun setUpSearchBarComponentInSearchViewWithoutSearchQuery() {
        composeTestRule.setContent {
            SearchBarComponent(
                state = HomeState(
                    isInSearchView = true,
                    searchQuery = ""
                ),
                event = {}
            )
        }
    }
}