package com.coderbyte.application.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coderbyte.application.views.ApplicationClass
import com.coderbyte.application.views.models.helper.NavigationModel
import com.coderbyte.application.views.models.helper.NotificationMessage
import com.coderbyte.application.views.utils.DisplayNotification
import com.coderbyte.application.views.utils.Event
import com.coderbyte.application.views.utils.TinyDB
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    protected val tinyDB by lazy { TinyDB.instance }


    private val _loader = MutableLiveData<Boolean>()

    val loader: LiveData<Boolean>
        get() = _loader


    private fun setLoader(flag: Boolean) {
        _loader.value = flag
    }

    private val _navigateTo = MutableLiveData<Event<NavigationModel>>()

    val navigateTo: LiveData<Event<NavigationModel>>
        get() = _navigateTo


    fun setNavigateTo(model: NavigationModel) {
        _navigateTo.value = Event(model)
    }

    private val _navigateBack = MutableLiveData<Event<Boolean>>()

    val navigateBack: LiveData<Event<Boolean>>
        get() = _navigateBack


    fun setNavigateBack(value: Boolean = true) {
        _navigateBack.value = Event(value)
    }

    private val job = SupervisorJob()

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }


    val coroutineScopeMain = CoroutineScope(Dispatchers.Main + coroutineExceptionHandler)


    protected val coroutineScope = CoroutineScope(job + Dispatchers.IO + coroutineExceptionHandler)

    protected open suspend fun toggleLoader(flag: Boolean) {
        withContext(Dispatchers.Main) {

            if (_loader.value != flag) {
                setLoader(flag)
            }

        }
    }


    private val _notificationMessage = MutableLiveData<NotificationMessage>()

    val notificationMessage: LiveData<NotificationMessage>
        get() = _notificationMessage


    fun setNotificationMessage(message: NotificationMessage) {
        _notificationMessage.value = message
    }

    fun callMessageNotification(
        msg: String,
        style: DisplayNotification.STYLE = DisplayNotification.STYLE.FAILURE
    ) {
        try {
            CoroutineScope(Dispatchers.Main).launch {

                setNotificationMessage(
                    NotificationMessage(message = msg, style = style)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    protected fun showErrorMessage(throwable: Throwable) {

        throwable.printStackTrace()

        when (throwable) {

            is SocketTimeoutException -> {
                callMessageNotification(
                    ApplicationClass.languageJson?.messages?.errorMessages?.internet ?: ""
                )
            }

            is UnknownHostException -> {
                callMessageNotification(
                    ApplicationClass.languageJson?.messages?.errorMessages?.internet ?: ""
                )
            }

            else -> {
                callMessageNotification(
                    ApplicationClass.languageJson?.messages?.errorMessages?.internal ?: ""
                )
            }
        }
    }

    protected fun handleServerError(errorBody: ResponseBody?) {

        errorBody?.apply {
            val error = Gson().fromJson(
                this.string(),
                Any::class.java //CHANGE TO SPECIFIC ERROR RESPONSE
            )


            CoroutineScope(Dispatchers.Main).launch {
                    callMessageNotification(error.toString())

            }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}