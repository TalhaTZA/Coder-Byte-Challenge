package com.coderbyte.application.views.fragments

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.transition.TransitionInflater
import com.coderbyte.application.R
import com.coderbyte.application.databinding.FragmentDefaultBinding
import com.coderbyte.application.databinding.FragmentProductDetailBinding
import com.coderbyte.application.views.ApplicationClass
import com.coderbyte.application.views.adapters.DataItemProductListing
import com.coderbyte.application.views.adapters.ImageViewPagerAdapter
import com.coderbyte.application.views.adapters.ListProductAdapter
import com.coderbyte.application.views.models.helper.NavigationModel
import com.coderbyte.application.views.utils.Enums
import com.coderbyte.application.views.utils.ItemClickListener
import com.coderbyte.application.views.viewmodels.MainActivityViewModel
import com.coderbyte.network_module.models.response.listing.Product


internal class ProductDetailFragment : BaseFragment() {

    private lateinit var mBinding: FragmentProductDetailBinding
    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var mAdapter: ImageViewPagerAdapter

    private var mProduct: Product? = null

    private lateinit var mAdapterListing: ListProductAdapter

    override fun init() {

        setSharedElementTransitionOnEnter()
        postponeEnterTransition()

        if (requireArguments().getParcelable<Product>(Enums.BundleFragmentKeys.PRODUCT.key) != null) {
            mProduct = requireArguments().getParcelable(Enums.BundleFragmentKeys.PRODUCT.key)
        }

        setAdapter()

        setData()

        setListingAdapter()


    }

    private fun setListingAdapter() {
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
                    id = R.id.action_productDetailFragment_self,
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

        mViewModel.responseProductListing.value?.apply {

//            postponeEnterTransition()
//            mBinding.recyclerViewProductListing.viewTreeObserver.addOnPreDrawListener {
//                startPostponedEnterTransition()
//                true
//            }

            if (!results.isNullOrEmpty()) {
                results?.map {
                    DataItemProductListing.ProductItemListingHorizontal(it)
                }?.let {
                    mAdapterListing.submitList(it.filter {
                        it.product != mProduct
                    })
                }
            }
        }
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

                mBinding.recyclerViewProductListing.viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }


                true
            }

            mAdapter =
                ImageViewPagerAdapter(requireActivity(), mProduct?.imageUrls ?: arrayListOf())

            viewPagerImage.adapter = mAdapter
        }
    }

    override fun setListeners() {
        mBinding.apply {
            toolBar.imgViewBack.setOnClickListener(this@ProductDetailFragment)
        }
    }

    override fun setLanguageData() {
        mBinding.apply {
            ApplicationClass.languageJson?.global?.apply {
                txtViewAllProducts.text = stringAllProducts
            }
        }

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
        when(v?.id){
            R.id.img_view_back->{
                mViewModel.setNavigateBack()
            }
        }
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

}