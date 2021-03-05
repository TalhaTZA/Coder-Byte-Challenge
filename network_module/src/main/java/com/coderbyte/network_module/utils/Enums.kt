package com.coderbyte.network_module.utils

import com.coderbyte.newtork_module.utils.Constants

internal object Enums {
    enum class RetrofitBaseUrl(val baseUrl: String) {
        BASE_URL(Constants.BASE_URL)
    }

    enum class TinyDBKeys(val key: String) {
        LOCALE("locale"),
        TOKEN_USER("auth_token_user")
    }
}