package com.murerwa.picnicinterview.fixtures

import com.murerwa.picnicinterview.features.home.data.models.DownsizedModel
import com.murerwa.picnicinterview.features.home.data.models.DownsizedSmallModel
import com.murerwa.picnicinterview.features.home.data.models.GifImageModel
import com.murerwa.picnicinterview.features.home.data.models.ImagesModel
import com.murerwa.picnicinterview.features.home.data.models.LoopingModel
import com.murerwa.picnicinterview.features.home.data.models.OriginalModel
import com.murerwa.picnicinterview.features.home.data.models.OriginalStillModel
import com.murerwa.picnicinterview.features.home.data.models.PreviewGifModel
import com.murerwa.picnicinterview.features.home.data.models.PreviewModel

val tImagesModel = ImagesModel(
    downsized = DownsizedModel(
        height = "height",
        width = "width",
        size = "size",
        url = "url"
    ),
    downsizedSmall = DownsizedSmallModel(
        height = "height",
        width = "width",
        mp4Size = "mp4Size",
        mp4 = "mp4"
    ),
    looping = LoopingModel(
        mp4Size = "mp4Size",
        mp4 = "mp4"
    ),
    original = OriginalModel(
        height = "height",
        width = "width",
        size = "size",
        url = "url",
        frames = "frames",
        mp4Size = "mp4Size",
        mp4 = "mp4",
        webpSize = "webpSize",
        webp = "webp",
        hash = "hash"
    ),
    originalStill = OriginalStillModel(
        height = "height",
        width = "width",
        size = "size",
        url = "url"
    ),
    preview = PreviewModel(
        height = "height",
        width = "width",
        mp4Size = "mp4Size",
        mp4 = "mp4"
    ),
    previewGif = PreviewGifModel(
        height = "height",
        width = "width",
        size = "size",
        url = "url"
    )
)

val tGifImageModel = GifImageModel(
    id = "id",
    images = tImagesModel,
    rating = "rating",
    title = "title",
    type = "type",
    url = "url",
    bitlyUrl = "bitlyUrl",
    username = "username"
)

val tGifImageModel2 = GifImageModel(
    id = "id_2",
    images = tImagesModel,
    rating = "rating_2",
    title = "title_2",
    type = "type_2",
    url = "url_2",
    bitlyUrl = "bitlyUrl_2",
    username = "username_2"
)