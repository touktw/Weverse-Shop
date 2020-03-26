package com.touktw.weverseshop.view.home

import android.view.View
import com.bumptech.glide.Glide
import com.touktw.weverseshop.model.ArtistDto
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_home_header_expanded.*

/**
 * Created by taekim on 2020-03-26
 */

class ExpandedHeaderContainer(override val containerView: View) : LayoutContainer {

    fun setArtist(artist: ArtistDto) {
        textArtistName.text = artist.name
        Glide.with(imageArtistLogo)
            .load(artist.logoUrl)
            .centerCrop()
            .into(imageArtistLogo)
    }
}