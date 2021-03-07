package com.coderbyte.application.views.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.coderbyte.application.views.fragments.ProductImageFragment


class ImageViewPagerAdapter(fa: FragmentActivity, private val list : List<String>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = ProductImageFragment(list[position])
}