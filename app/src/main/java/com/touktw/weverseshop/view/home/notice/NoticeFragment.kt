package com.touktw.weverseshop.view.home.notice

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R
import com.touktw.weverseshop.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_notice.*

class NoticeFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_notice
    private var homeViewModel: HomeViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            homeViewModel = ViewModelProvider(it, ViewModelProvider.AndroidViewModelFactory.getInstance(it.application))
                    .get(HomeViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.setHasFixedSize(false)
        recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        homeViewModel?.notice?.observe(viewLifecycleOwner, Observer {
            val list = it ?: return@Observer
            list.sortedByDescending { it.createAt }.let {
                recyclerView.adapter = NoticeAdapter(it)
            }
        })
    }
}