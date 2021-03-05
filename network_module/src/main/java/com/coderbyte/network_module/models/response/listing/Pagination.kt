package com.coderbyte.network_module.models.response.listing


import com.squareup.moshi.Json

data class Pagination(
    @Json(name = "key")
    val key: Any? = null
)