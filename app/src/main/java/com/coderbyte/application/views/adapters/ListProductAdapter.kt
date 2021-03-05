package com.coderbyte.application.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coderbyte.application.databinding.ItemShimmerLoaderBinding
import com.coderbyte.application.views.utils.ItemClickListener
import com.coderbyte.network_module.models.response.listing.Product

private const val ITEM_VIEW_TYPE_PRODUCT = 0
private const val ITEM_VIEW_TYPE_LOADER = 1


class ListProductAdapter(
    private val clickListener: ItemClickListener<DataItemProductListing>
) :
    ListAdapter<DataItemProductListing, RecyclerView.ViewHolder>(DiffCallbackProductList()) {


    override fun getItemViewType(position: Int): Int {

        return when (getItem(position)) {
            is DataItemProductListing.ProductItemListing -> ITEM_VIEW_TYPE_PRODUCT

            is DataItemProductListing.ShimmerLoader -> ITEM_VIEW_TYPE_LOADER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)


        return when (viewType) {


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


            is ShimmerLoaderViewHolder -> {
                holder.bind()
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
