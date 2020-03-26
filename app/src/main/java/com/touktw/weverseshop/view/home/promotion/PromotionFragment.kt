package com.touktw.weverseshop.view.home.promotion

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R
import kotlinx.android.synthetic.main.fragment_promotion.*

/**
 * Created by taekim on 2020-03-26
 */


class PromotionFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_promotion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dpScale = resources.displayMetrics.density
        Log.d("###", "dpScale $dpScale")
        viewPager.adapter = PromotionAdapter(parentFragmentManager)
        viewPager.clipToPadding = false
        viewPager.pageMargin = dpScale.times(8).toInt()
        viewPager.setPadding(dpScale.times(14).toInt(),
                dpScale.times(8).toInt(),
                dpScale.times(14).toInt(),
                dpScale.times(8).toInt())


    }
}

class PromotionAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return PromotionItemFragment()
    }

    override fun getCount(): Int {
        return 5
    }
}