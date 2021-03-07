package com.coderbyte.application.views.fragments

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.coderbyte.application.R
import com.coderbyte.application.databinding.FragmentProductListingBinding
import com.coderbyte.application.views.adapters.DataItemProductListing
import com.coderbyte.application.views.adapters.ListProductAdapter
import com.coderbyte.application.views.models.helper.NavigationModel
import com.coderbyte.application.views.utils.Constants
import com.coderbyte.application.views.utils.Enums
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
        mViewModel.responseProductListing.value ?: mViewModel.callServerForProductListing()

        setAdapter()
    }

    private fun setAdapter() {

        mAdapterListing = ListProductAdapter(ItemClickListener {

                product, view ->

            var url = ""

            if (!product.imageUrls.isNullOrEmpty()) {
                product.imageUrls?.find {
                    it.isNotEmpty()
                }?.apply {
                    url = this
                }
            }

            mViewModel.setNavigateTo(
                NavigationModel(
                    id = R.id.action_productListingFragment_to_productDetailFragment,
                    bundle = bundleOf(
                        Enums.BundleFragmentKeys.PRODUCT.key to product
                    ),
                    fragNavigatorExtras = FragmentNavigatorExtras(
                        view to url
                    )
                )
            )
        })



        mBinding.recyclerViewProductListing.adapter = mAdapterListing


        mViewModel.responseProductListing.value ?: submitShimmer()

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
        mBinding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false

                mViewModel.callServerForProductListing()

                submitShimmer()

            }
        }
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

            postponeEnterTransition()
            mBinding.recyclerViewProductListing.viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

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