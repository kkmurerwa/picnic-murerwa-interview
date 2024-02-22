package com.murerwa.picnicinterview.features.home.presentation.navgraph

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.murerwa.picnicinterview.features.home.domain.entities.GifImage

class ParcelableParamType : NavType<GifImage>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): GifImage? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): GifImage {
        return Gson().fromJson(value, GifImage::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: GifImage) {
        bundle.putParcelable(key, value)
    }
}