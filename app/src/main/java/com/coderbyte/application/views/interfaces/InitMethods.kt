package com.coderbyte.application.views.interfaces

import android.view.View

internal interface InitMethods : View.OnClickListener {

    fun getFragmentLayout(): Int

    fun getViewBinding()

    fun getViewModel()

    fun observe()

    fun setLiveDataValues()

    fun init()

    fun setListeners()

    fun setLanguageData()

}