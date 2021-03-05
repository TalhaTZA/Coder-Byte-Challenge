package com.coderbyte.network_module.repository


import com.coderbyte.network_module.models.response.listing.ResponseListing
import com.coderbyte.network_module.utils.Enums
import retrofit2.Response
import java.lang.Exception

object ApiRepository {

    private val api = RetrofitBuilder.getRetrofitInstance(Enums.RetrofitBaseUrl.BASE_URL)

    suspend fun callApiForListingProducts(): Result<Response<ResponseListing>> {

        return try {
            Success(api.getProductListing())
        } catch (e: Exception) {
            Error(e)
        }
    }

}