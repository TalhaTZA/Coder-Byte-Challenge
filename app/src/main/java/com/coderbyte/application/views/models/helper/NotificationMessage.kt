package com.coderbyte.application.views.models.helper

import com.coderbyte.application.views.utils.DisplayNotification


data class NotificationMessage(
    var show: Boolean = true,
    var message: String = "",
    var style: DisplayNotification.STYLE = DisplayNotification.STYLE.INFO
)