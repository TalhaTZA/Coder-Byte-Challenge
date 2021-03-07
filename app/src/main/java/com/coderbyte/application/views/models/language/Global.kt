package com.coderbyte.application.views.models.language


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Global(
    @SerializedName("string_all_products")
    @Expose
    val stringAllProducts: String = ""
)