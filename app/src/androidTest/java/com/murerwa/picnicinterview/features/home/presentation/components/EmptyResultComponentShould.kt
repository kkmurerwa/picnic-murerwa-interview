package com.murerwa.picnicinterview.features.home.presentation.components

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import com.murerwa.picnicinterview.R
import com.murerwa.picnicinterview.utils.BaseComposeTest
import org.junit.Before
import org.junit.Test

class EmptyResultComponentShould: BaseComposeTest() {
    private val message = "No results found"
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            EmptyResultComponent(message)
        }
    }

    @Test
    fun containATextComposableWithExpectedTraits() {
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_empty_result_text))
            .assertIsDisplayed()
            .assertTextContains(message)
    }

    @Test
    fun containAnImageComposableWithExpectedTraits() {
        composeTestRule
            .onNodeWithTag(context.getString(R.string.tag_empty_result_image))
            .assertIsDisplayed()
    }
}