package com.coderbyte.application.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coderbyte.network_module.models.response.listing.ResponseListing
import com.coderbyte.network_module.repository.ApiRepository
import com.coderbyte.network_module.repository.onError
import com.coderbyte.network_module.repository.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class MainActivityViewModel : BaseViewModel() {

    /*DATA SECTION*/

    private val _responseProductListing = MutableLiveData<ResponseListing>()

    val responseProductListing: LiveData<ResponseListing>
        get() = _responseProductListing


    /*API SECTION*/

    fun callServerForProductListing() {

        coroutineScope.launch {

            toggleLoader(true)

            val data = ApiRepository.callApiForListingProducts()

            data.onSuccess {
                if (it.isSuccessful) {

                    withContext(Dispatchers.Main) {
                        _responseProductListing.value = it.body()
                    }


                } else {
                    handleServerError(it.errorBody())
                }

            }.onError {
                showErrorMessage(it.exception)
            }


            toggleLoader(false)

        }

    }


    public override suspend fun toggleLoader(flag: Boolean) {
        super.toggleLoader(flag)
    }


}