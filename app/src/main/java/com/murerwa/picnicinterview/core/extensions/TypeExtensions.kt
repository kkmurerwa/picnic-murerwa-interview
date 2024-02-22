package com.murerwa.picnicinterview.core.extensions

import android.content.Context
import com.murerwa.picnicinterview.R
import java.util.Locale

/**
 * This function is used to capitalize the first letter of a String and return the default string if
 * it is empty.
 * @param context The context of the application.
 * @return [String] with the first letter capitalized or default string if empty.
 */
fun String.capitalizeFirstLetter(context: Context, defaultString: String = context.getString(R.string.no_title)): String {
    val title = if (this.isNotEmpty()) {
        this.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
    } else {
        defaultString
    }

    return title
}