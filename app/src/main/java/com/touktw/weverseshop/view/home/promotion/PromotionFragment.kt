package com.touktw.weverseshop.view.home.promotion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R
import com.touktw.weverseshop.model.PromotionDto
import com.touktw.weverseshop.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_promotion.*

/**
 * Created by taekim on 2020-03-26
 */


class PromotionFragment : BaseFragment() {
    private var homeViewModel: HomeViewModel? = null
    override val layoutRes: Int
        get() = R.layout.fragment_promotion

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

        val dpScale = resources.displayMetrics.density
        viewPager.clipToPadding = false
        viewPager.pageMargin = dpScale.times(8).toInt()
        viewPager.setPadding(dpScale.times(14).toInt(),
                dpScale.times(8).toInt(),
                dpScale.times(14).toInt(),
                dpScale.times(8).toInt())
        homeViewModel?.promotions?.observe(viewLifecycleOwner, Observer {
            viewPager.adapter = PromotionAdapter(parentFragmentManager, it)
        })
    }
}

class PromotionAdapter(fragmentManager: FragmentManager, val items: List<PromotionDto>) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return PromotionItemFragment.newInstance(items[position])
    }

    override fun getCount(): Int {
        return items.size
    }
}