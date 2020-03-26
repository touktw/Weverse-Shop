package com.touktw.weverseshop.view.home

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.touktw.weverseshop.R
import com.touktw.weverseshop.base.BaseActivity
import com.touktw.weverseshop.model.ArtistDto
import com.touktw.weverseshop.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by taekim on 2020-03-24
 */


class HomeActivity : BaseActivity() {
    private val expandedHeaderContainer: ExpandedHeaderContainer by lazy {
        ExpandedHeaderContainer(layoutExpanded)
    }
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initCollapsingLayout()

        homeViewModel.selectedArtist.observe(this, Observer {
            setArtistInfo(it)
        })
        buttonUp.setOnClickListener {
            scrollView.smoothScrollTo(0, 0)
            appBar.setExpanded(true, true)
        }
    }


    private fun initCollapsingLayout() {
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                collapsingToolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsingToolbar) -> {
                    //collapsed
                    layoutExpanded.visibility = View.INVISIBLE
                    textToolbar.visibility = View.VISIBLE
                }

                else -> {
                    //extended
                    layoutExpanded.visibility = View.VISIBLE
                    textToolbar.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun setArtistInfo(artistDto: ArtistDto) {
        expandedHeaderContainer.setArtist(artistDto)
        textToolbar.text = artistDto.name
    }
}