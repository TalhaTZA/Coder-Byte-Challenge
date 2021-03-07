package com.coderbyte.application.views.fragments

import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.coderbyte.application.R
import com.coderbyte.application.databinding.FragmentDefaultBinding
import com.coderbyte.application.databinding.FragmentProductDetailBinding
import com.coderbyte.application.views.adapters.ImageViewPagerAdapter
import com.coderbyte.application.views.utils.Enums
import com.coderbyte.application.views.viewmodels.MainActivityViewModel
import com.coderbyte.network_module.models.response.listing.Product


internal class ProductDetailFragment : BaseFragment() {

    private lateinit var mBinding: FragmentProductDetailBinding
    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var mAdapter: ImageViewPagerAdapter

    private var mProduct: Product? = null

    override fun init() {

        setSharedElementTransitionOnEnter()
        postponeEnterTransition()

        if (requireArguments().getParcelable<Product>(Enums.BundleFragmentKeys.PRODUCT.key) != null) {
            mProduct = requireArguments().getParcelable(Enums.BundleFragmentKeys.PRODUCT.key)
        }

        setAdapter()

        setData()
    }

    private fun setData() {
        mBinding.apply {

            mProduct?.apply {
                txtViewProductName.text = name
                txtViewProductPrice.text = price
            }

        }
    }

    private fun setAdapter() {
        mBinding.apply {

            viewPagerImage.viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

            mAdapter =
                ImageViewPagerAdapter(requireActivity(), mProduct?.imageUrls ?: arrayListOf())

            viewPagerImage.adapter = mAdapter
        }
    }

    override fun setListeners() {
    }

    override fun setLanguageData() {

    }

    override fun getFragmentLayout() = R.layout.fragment_product_detail

    override fun getViewBinding() {
        mBinding = binding as FragmentProductDetailBinding
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

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

}