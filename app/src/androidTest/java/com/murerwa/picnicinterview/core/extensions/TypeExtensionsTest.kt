package com.murerwa.picnicinterview.core.extensions

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.murerwa.picnicinterview.utils.BaseComposeTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TypeExtensionsTest: BaseComposeTest() {
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun capitalizeFirstLetterShouldCapitalizeFirstLetter() {
        val input = "hello"
        val expected = "Hello"
        val actual = input.capitalizeFirstLetter(context)
        assertEquals(expected, actual)
    }
}