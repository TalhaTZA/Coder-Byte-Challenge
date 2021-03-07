package com.coderbyte.application.views.fragments

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.coderbyte.application.R
import com.coderbyte.application.databinding.FragmentProductListingBinding
import com.coderbyte.application.views.adapters.DataItemProductListing
import com.coderbyte.application.views.adapters.ListProductAdapter
import com.coderbyte.application.views.utils.Constants
import com.coderbyte.application.views.utils.ItemClickListener
import com.coderbyte.application.views.utils.T
import com.coderbyte.application.views.viewmodels.MainActivityViewModel
import java.util.*
import kotlin.collections.ArrayList


internal class ProductListingFragment : BaseFragment() {

    private lateinit var mBinding: FragmentProductListingBinding
    private lateinit var mViewModel: MainActivityViewModel

    private lateinit var mAdapterListing: ListProductAdapter
    private lateinit var mProductListing: ArrayList<DataItemProductListing>


    override fun init() {
        mViewModel.callServerForProductListing()

        setAdapter()
    }

    private fun setAdapter() {
        mAdapterListing = ListProductAdapter(ItemClickListener {

        })

        mBinding.recyclerViewProductListing.adapter = mAdapterListing


        submitShimmer()

    }

    private fun submitShimmer() {
        mProductListing = getShimmerList()

        mAdapterListing.submitList(mProductListing)
    }

    private fun getShimmerList(seed: Int = Constants.SHIMMER_RANDOM_SEED): ArrayList<DataItemProductListing> {

        val temp = arrayListOf<DataItemProductListing>()

        for (i in 0..(Random().nextInt(seed).inc() + 10)) {
            temp.add(DataItemProductListing.ShimmerLoader())
        }

        return temp
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


                setAdapterData()
            })


        }
    }

    private fun setAdapterData() {
        mViewModel.responseProductListing.value?.apply {

            if (!results.isNullOrEmpty()) {
                results?.map {
                    DataItemProductListing.ProductItemListing(it)
                }?.let {
                    mAdapterListing.submitList(it)
                }
            }
        }
    }

    override fun setLiveDataValues() {
        setAdapterData()
    }

    override fun onClick(v: View?) {

    }

}