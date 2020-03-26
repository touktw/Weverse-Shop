package com.touktw.weverseshop.view.home.recent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R
import com.touktw.weverseshop.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_recent.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecentFragment : BaseFragment() {
    override val layoutRes: Int
        get() = R.layout.fragment_recent
    private var homeViewModel: HomeViewModel? = null
    private val adapter = RecentProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            homeViewModel = ViewModelProvider(it, ViewModelProvider.AndroidViewModelFactory.getInstance(it.application))
                    .get(HomeViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        homeViewModel?.recent?.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }

            CoroutineScope(Dispatchers.Main).launch {
                // TODO Diff Utils
                adapter.items.clear()
                adapter.items.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}