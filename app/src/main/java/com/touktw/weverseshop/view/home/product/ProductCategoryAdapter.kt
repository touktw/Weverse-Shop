package com.touktw.weverseshop.view.home.product

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ProductCategoryAdapter(fragmentManager: FragmentManager, private val items: List<String>) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return ProductCategoryFragment.newInstance(items[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }
}