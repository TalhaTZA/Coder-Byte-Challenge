package com.coderbyte.newtork_module.utils

import okhttp3.logging.HttpLoggingInterceptor


internal object Constants {

    const val BASE_URL = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/"

    val LOG_LEVEL_API = HttpLoggingInterceptor.Level.BODY

    const val API_CONNECT_TIMEOUT: Long = 10

    const val API_READ_TIMEOUT: Long = 10

    const val API_WRITE_TIMEOUT: Long = 10

}
