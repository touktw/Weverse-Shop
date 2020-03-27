package com.touktw.weverseshop.view.home.product

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R
import com.touktw.weverseshop.viewmodel.HomeViewModel
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

    private var homeViewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            homeViewModel = ViewModelProvider(it,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(it.application))
                    .get(HomeViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout.setupWithViewPager(viewPagerProduct)
        tabLayout.measureAllChildren = true

        homeViewModel?.productsByCategory?.observe(viewLifecycleOwner, Observer { map ->
            val map = map ?: return@Observer
            CoroutineScope(Dispatchers.Main).launch {
                val categories = map.keys.toList()
                viewPagerProduct.offscreenPageLimit = 10
                viewPagerProduct.adapter = ProductCategoryAdapter(childFragmentManager, categories)
            }
        })
    }
}