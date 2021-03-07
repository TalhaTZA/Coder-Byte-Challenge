package com.coderbyte.application.views.fragments

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.coderbyte.application.R
import com.coderbyte.application.databinding.FragmentProductImageBinding
import com.coderbyte.application.views.utils.loadImage
import com.coderbyte.application.views.viewmodels.MainActivityViewModel


internal class ProductImageFragment(
    private val image: String,
    private val fragment: Fragment
) : BaseFragment() {

    private lateinit var mBinding: FragmentProductImageBinding
    private lateinit var mViewModel: MainActivityViewModel

    override fun init() {

        mBinding.apply {

            imgViewProduct.transitionName = image

            fragment.loadImage(
                image, imgViewProduct,
                CircleCrop()
            )
        }


    }

    override fun setListeners() {
    }

    override fun setLanguageData() {

    }

    override fun getFragmentLayout() = R.layout.fragment_product_image

    override fun getViewBinding() {
        mBinding = binding as FragmentProductImageBinding
    }

    override fun getViewModel() {
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun observe() {
    }

    override fun setLiveDataValues() {

    }

    override fun onClick(v: View?) {

    }

}