package com.coderbyte.application.views.fragments

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coderbyte.application.R
import com.coderbyte.application.databinding.FragmentProductListingBinding
import com.coderbyte.application.views.utils.T
import com.coderbyte.application.views.viewmodels.MainActivityViewModel


internal class ProductListingFragment : BaseFragment() {

    private lateinit var mBinding: FragmentProductListingBinding
    private lateinit var mViewModel: MainActivityViewModel

    override fun init() {
        mViewModel.callServerForProductListing()
    }

    override fun setListeners() {
    }

    override fun setLanguageData() {

    }

    override fun getFragmentLayout() = R.layout.fragment_product_listing

    override fun getViewBinding() {
        mBinding = binding as FragmentProductListingBinding
    }

    override fun getViewModel() {
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun observe() {
        mViewModel.apply {

            responseProductListing.observe(this@ProductListingFragment, Observer {
                it ?: return@Observer


                requireContext().T("${it.results?.size ?: 0}")
            })


        }
    }

    override fun setLiveDataValues() {

    }

    override fun onClick(v: View?) {

    }

}