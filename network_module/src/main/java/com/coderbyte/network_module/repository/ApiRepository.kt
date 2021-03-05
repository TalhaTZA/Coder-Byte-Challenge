package com.coderbyte.network_module.repository


import com.coderbyte.network_module.models.response.ResponseGeneral
import com.coderbyte.network_module.utils.Enums
import retrofit2.Response
import java.lang.Exception

object ApiRepository {

    private val api = RetrofitBuilder.getRetrofitInstance(Enums.RetrofitBaseUrl.BASE_URL)

    suspend fun callApi(): Result<Response<ResponseGeneral<Any>>> {

        return try {
            Success(api.getTodos())
        } catch (e: Exception) {
            Error(e)
        }
    }

}