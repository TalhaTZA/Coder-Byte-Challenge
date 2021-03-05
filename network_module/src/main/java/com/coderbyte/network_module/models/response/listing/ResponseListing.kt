package com.coderbyte.network_module.models.response.listing


import com.squareup.moshi.Json

data class ResponseListing(
    @Json(name = "pagination")
    val pagination: Pagination? = null,
    @Json(name = "results")
    val results: List<Result>? = null
)