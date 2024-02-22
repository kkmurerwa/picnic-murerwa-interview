package com.murerwa.picnicinterview.features.home.data.models

data class SearchGifResponseModel(
    val data: List<GifImageModel>,
    val meta: MetaModel,
    val pagination: PaginationModel
)