package com.coderbyte.network_module.models.response.listing


import com.squareup.moshi.Json

data class Result(
    @Json(name = "created_at")
    val createdAt: String? = null,
    @Json(name = "image_ids")
    val imageIds: List<String>? = null,
    @Json(name = "image_urls")
    val imageUrls: List<String>? = null,
    @Json(name = "image_urls_thumbnails")
    val imageUrlsThumbnails: List<String>? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "price")
    val price: String? = null,
    @Json(name = "uid")
    val uid: String? = null
)