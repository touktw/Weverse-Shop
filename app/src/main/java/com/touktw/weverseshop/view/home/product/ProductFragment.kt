package com.touktw.weverseshop.view.home.product

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R
import kotlinx.android.synthetic.main.fragment_product.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by taekim on 2020-03-26
 */

class ProductFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_product

    val adapter: ProductCategoryAdapter by lazy {
        ProductCategoryAdapter(childFragmentManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerProduct.adapter = adapter
        tabLayout.setupWithViewPager(viewPagerProduct)


        CoroutineScope(Dispatchers.Main).launch {
            tabLayout.getTabAt(0)?.text = "00"
            tabLayout.getTabAt(1)?.text = "11"
        }
    }
}


class ProductCategoryAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        Log.d("###", "getItem from ProductCategoryAdapter position:$position")
        return ProductCategoryFragment()
    }

    override fun getCount(): Int {
        return 5
    }
}