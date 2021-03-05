package com.coderbyte.application.views.viewmodels

import com.coderbyte.network_module.repository.ApiRepository
import com.coderbyte.network_module.repository.onError
import com.coderbyte.network_module.repository.onSuccess
import kotlinx.coroutines.launch

internal class MainActivityViewModel : BaseViewModel() {

    /*DATA SECTION*/


    /*API SECTION*/

    fun callServerFor() {

        coroutineScope.launch {

            toggleLoader(true)

            val data = ApiRepository.callApi()

            data.onSuccess {
                if (it.isSuccessful) {
                    // set data to live data
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