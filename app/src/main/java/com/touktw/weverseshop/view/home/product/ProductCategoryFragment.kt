package com.touktw.weverseshop.view.home.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.touktw.weverseshop.R
import com.touktw.weverseshop.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_product_category.*

/**
 * Created by taekim on 2020-03-26
 */

class ProductCategoryFragment : Fragment() {
    private var homeViewModel: HomeViewModel? = null
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        category = arguments?.getString(KEY_CATEGORY)
        activity?.let {
            homeViewModel = ViewModelProvider(it,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(it.application))
                    .get(HomeViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.setHasFixedSize(false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL).apply {
            setDrawable(context?.resources?.getDrawable(R.drawable.divider_trans)!!)
        })
        homeViewModel?.productsByCategory?.observe(viewLifecycleOwner, Observer {
            val map = it ?: return@Observer
            map[category]?.chunked(MAX_ITEM_COUNT)?.get(0)?.let {
                recyclerView.adapter = ProductAdapter(it, homeViewModel?.currency?.value?.symbol)
            }
        })
    }

    companion object {
        private const val MAX_ITEM_COUNT = 6
        private const val KEY_CATEGORY = "CATEGORY"
        fun newInstance(category: String): ProductCategoryFragment {
            return ProductCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_CATEGORY, category)
                }
            }
        }
    }
}