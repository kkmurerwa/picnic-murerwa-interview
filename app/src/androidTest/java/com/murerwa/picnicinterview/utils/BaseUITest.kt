package com.murerwa.picnicinterview.utils

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.murerwa.picnicinterview.MainActivity
import org.junit.Rule

open class BaseUITest {
    @get:Rule
    var composeAndroidTestRule = createAndroidComposeRule<MainActivity>()
}