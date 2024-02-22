package com.murerwa.picnicinterview.features.home.data.models

import com.murerwa.picnicinterview.fixtures.tImagesModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ImagesModelShould {
    @Test
    fun returnImageUrlsObjectIfToImageUrlsIsCalled() {
        val imageUrls = tImagesModel.toImageUrls()

        assertEquals(tImagesModel.downsized.url, imageUrls.downsized)
        assertEquals(tImagesModel.downsizedSmall.mp4, imageUrls.downsizedSmall)
        assertEquals(tImagesModel.looping.mp4, imageUrls.looping)
        assertEquals(tImagesModel.original.url, imageUrls.original)
        assertEquals(tImagesModel.originalStill.url, imageUrls.originalStill)
        assertEquals(tImagesModel.preview.mp4, imageUrls.preview)
        assertEquals(tImagesModel.previewGif.url, imageUrls.previewGif)
    }
}