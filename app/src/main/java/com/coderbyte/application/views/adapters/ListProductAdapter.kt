package com.coderbyte.application.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.coderbyte.application.databinding.ItemProductListBinding
import com.coderbyte.application.databinding.ItemShimmerLoaderBinding
import com.coderbyte.application.views.ApplicationClass
import com.coderbyte.application.views.utils.Constants
import com.coderbyte.application.views.utils.ItemClickListener
import com.coderbyte.application.views.utils.loadImage
import com.coderbyte.network_module.models.response.listing.Product

private const val ITEM_VIEW_TYPE_PRODUCT = 0
private const val ITEM_VIEW_TYPE_LOADER = 1


class ListProductAdapter(
    private val clickListener: ItemClickListener<Product>
) :
    ListAdapter<DataItemProductListing, RecyclerView.ViewHolder>(DiffCallbackProductList()) {

    private val mContext by lazy { ApplicationClass.application }


    override fun getItemViewType(position: Int): Int {

        return when (getItem(position)) {
            is DataItemProductListing.ProductItemListing -> ITEM_VIEW_TYPE_PRODUCT

            is DataItemProductListing.ShimmerLoader -> ITEM_VIEW_TYPE_LOADER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)


        return when (viewType) {

            ITEM_VIEW_TYPE_PRODUCT -> {
                val binding = ItemProductListBinding.inflate(layoutInflater, parent, false)
                ProductListingViewHolder(binding)
            }


            ITEM_VIEW_TYPE_LOADER -> {
                val binding = ItemShimmerLoaderBinding.inflate(layoutInflater, parent, false)
                ShimmerLoaderViewHolder(binding)
            }


            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val modelProductItem = getItem(position) as DataItemProductListing

        when (holder) {

            is ProductListingViewHolder -> {
                holder.bind(
                    modelProductItem as DataItemProductListing.ProductItemListing,
                    clickListener
                )
            }

            is ShimmerLoaderViewHolder -> {
                holder.bind()
            }
        }
    }

    private inner class ProductListingViewHolder(private val binding: ItemProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            modelProductItem: DataItemProductListing.ProductItemListing,
            itemClickListener: ItemClickListener<Product>
        ) {
            binding.apply {

                modelProductItem.product.apply {
                    model = this

                    clickListener = itemClickListener

                    if (!imageUrls.isNullOrEmpty()) {
                        imageUrls?.find {
                            it.isNotEmpty()
                        }?.let {
                            frameLayoutHolder.transitionName = it
                        }

                    }


                    if (!imageUrlsThumbnails.isNullOrEmpty()) {
                        imageUrlsThumbnails?.find {
                            it.isNotEmpty()
                        }?.let {

                            mContext.loadImage(
                                it,
                                imgViewProduct,
                                RoundedCorners(Constants.ROUND_CORNER_RADIUS)
                            )
                        }

                    }
                }


                executePendingBindings()
            }
        }

    }


    private inner class ShimmerLoaderViewHolder(private val binding: ItemShimmerLoaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {

                executePendingBindings()
            }
        }

    }

}


sealed class DataItemProductListing {

    data class ProductItemListing(val product: Product) :
        DataItemProductListing() {
        override val id = product.uid ?: ""
    }


    data class ShimmerLoader(val loaderId: String = "-1") : DataItemProductListing() {
        override val id = loaderId
    }


    abstract val id: String


}

class DiffCallbackProductList :
    DiffUtil.ItemCallback<DataItemProductListing>() {

    override fun areItemsTheSame(
        oldItem: DataItemProductListing,
        newItem: DataItemProductListing
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DataItemProductListing,
        newItem: DataItemProductListing
    ): Boolean {
        return oldItem == newItem
    }
}
