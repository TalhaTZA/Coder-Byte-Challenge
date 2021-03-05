package com.coderbyte.application.views

import android.app.Application
import com.coderbyte.application.views.models.language.LanguageJson
import com.coderbyte.application.views.utils.loadJSONFromAssets
import com.coderbyte.network_module.NetworkModule
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


internal class ApplicationClass : Application() {

    override fun onCreate() {

        super.onCreate()

        mApplicationClass = this

        setUpLanguageJson()

        NetworkModule.initialize(this.applicationContext)

    }

    private fun setUpLanguageJson() {
        CoroutineScope(Dispatchers.IO).launch {
            languageJson =
                Gson().fromJson(loadJSONFromAssets("AppAndroidEn.json"), LanguageJson::class.java)
        }
    }


    companion object {

        @JvmStatic
        private lateinit var mApplicationClass: ApplicationClass

        @JvmStatic
        val application: ApplicationClass by lazy { mApplicationClass }


        @JvmStatic
        internal var languageJson: LanguageJson? = null
    }
}