package com.coderbyte.network_module.repository



import com.coderbyte.network_module.models.response.listing.ResponseListing
import retrofit2.Response
import retrofit2.http.*

internal interface RetrofitAPI {

    companion object {
        const val HEADER_POSTFIX = ": "
        const val HEADER_TAG = "@"
        const val HEADER_TAG_PUBLIC = "public"
    }

    /*POST REQUEST*/


    /*GET REQUEST*/

    @GET("default/dynamodb-writer")
    suspend fun getProductListing(): Response<ResponseListing>


    /*PUT REQUEST*/


    /*DELETE*/


}