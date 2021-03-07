package com.coderbyte.application.views.fragments

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.coderbyte.application.R
import com.coderbyte.application.databinding.FragmentProductImageBinding
import com.coderbyte.application.views.utils.Constants
import com.coderbyte.application.views.utils.loadImage
import com.coderbyte.application.views.viewmodels.MainActivityViewModel
import com.coderbyte.network_module.models.response.listing.Product


internal class ProductImageFragment(private val image: String) : BaseFragment() {

    private lateinit var mBinding: FragmentProductImageBinding
    private lateinit var mViewModel: MainActivityViewModel

    override fun init() {
        requireContext().loadImage(
            image, mBinding.imgViewProduct,
            CircleCrop()
        )
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