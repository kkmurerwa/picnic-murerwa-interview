package com.murerwa.picnicinterview.features.home.data.models

import com.google.gson.annotations.SerializedName
import com.murerwa.picnicinterview.features.home.domain.entities.ImageUrls

data class ImagesModel(
    val downsized: DownsizedModel,
    @SerializedName("downsized_small")
    val downsizedSmall: DownsizedSmallModel,
    val looping: LoopingModel,
    val original: OriginalModel,
    @SerializedName("original_still")
    val originalStill: OriginalStillModel,
    val preview: PreviewModel,
    @SerializedName("preview_gif")
    val previewGif: PreviewGifModel
) {
    fun toImageUrls(): ImageUrls {
        return ImageUrls(
            downsized = downsized.url,
            downsizedSmall = downsizedSmall.mp4,
            looping = looping.mp4,
            original = original.url,
            originalStill = originalStill.url,
            preview = preview.mp4,
            previewGif = previewGif.url
        )
    }
}