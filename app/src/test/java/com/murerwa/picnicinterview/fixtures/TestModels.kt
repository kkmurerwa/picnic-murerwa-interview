package com.murerwa.picnicinterview.fixtures

import com.murerwa.picnicinterview.features.home.data.models.DownsizedModel
import com.murerwa.picnicinterview.features.home.data.models.DownsizedSmallModel
import com.murerwa.picnicinterview.features.home.data.models.GifImageModel
import com.murerwa.picnicinterview.features.home.data.models.GifImageResponseModel
import com.murerwa.picnicinterview.features.home.data.models.ImagesModel
import com.murerwa.picnicinterview.features.home.data.models.LoopingModel
import com.murerwa.picnicinterview.features.home.data.models.MetaModel
import com.murerwa.picnicinterview.features.home.data.models.OriginalModel
import com.murerwa.picnicinterview.features.home.data.models.OriginalStillModel
import com.murerwa.picnicinterview.features.home.data.models.PaginationModel
import com.murerwa.picnicinterview.features.home.data.models.PreviewGifModel
import com.murerwa.picnicinterview.features.home.data.models.PreviewModel
import com.murerwa.picnicinterview.features.home.data.models.SearchGifResponseModel

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

val tMetaModel = MetaModel(
    status = 200,
    msg = "OK",
    responseId = "responseId"
)

val tMetaModelFailed = MetaModel(
    status = 400,
    msg = "Bad Request",
    responseId = "responseId"
)

val tPaginationModel = PaginationModel(
    totalCount = 100,
    count = 10,
    offset = 0
)

val tGifImageResponseModel = GifImageResponseModel(
    data = tGifImageModel,
    meta = tMetaModel
)

val tGifImageResponseModelFailed = GifImageResponseModel(
    data = tGifImageModel,
    meta = tMetaModelFailed
)

val tSearchGifResponseModel = SearchGifResponseModel(
    data = listOf(tGifImageModel),
    meta = tMetaModel,
    pagination = tPaginationModel
)