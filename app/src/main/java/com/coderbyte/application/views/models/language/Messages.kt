package com.coderbyte.application.views.models.language


import com.google.gson.annotations.SerializedName

 data class Messages(
    @SerializedName("error_messages")
    val errorMessages: ErrorMessages
)