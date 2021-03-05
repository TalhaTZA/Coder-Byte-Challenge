package com.coderbyte.application.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.coderbyte.application.views.utils.TinyDB

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var tinydb: TinyDB
    protected lateinit var binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tinydb = TinyDB.instance

        init()


    }

    private fun init() {

        binding = DataBindingUtil.setContentView(this, getActivityLayout())

        getViewBinding()

    }

    abstract fun getActivityLayout(): Int

    abstract fun getViewBinding()


}