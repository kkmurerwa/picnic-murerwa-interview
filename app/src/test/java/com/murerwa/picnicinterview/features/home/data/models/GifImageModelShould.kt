package com.murerwa.picnicinterview.features.home.data.models

import com.murerwa.picnicinterview.fixtures.tGifImageModel
import org.junit.Assert.assertEquals
import org.junit.Test

class GifImageModelShould {
    @Test
    fun returnGifImageObjectIfToGifImageIsCalled() {
        val gifImage = tGifImageModel.toGifImage()

        assertEquals(tGifImageModel.id, gifImage.id)
        assertEquals(tGifImageModel.images.toImageUrls(), gifImage.imageUrls)
        assertEquals(tGifImageModel.rating, gifImage.rating)
        assertEquals(tGifImageModel.title, gifImage.title)
        assertEquals(tGifImageModel.type, gifImage.type)
        assertEquals(tGifImageModel.url, gifImage.url)
        assertEquals(tGifImageModel.bitlyUrl, gifImage.bitlyUrl)
        assertEquals(tGifImageModel.username, gifImage.username)
    }
}