package com.touktw.weverseshop.view.wizard

import android.view.View
import android.widget.RadioButton
import com.bumptech.glide.Glide
import com.touktw.weverseshop.model.ArtistDto
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_image_radio_button.*


class ArtistRadioButtonContainer(override val containerView: View) : LayoutContainer {
    fun getRadioButton(): RadioButton = radioButton
    fun setData(artist: ArtistDto) {
        radioButton.text = artist.name
        Glide.with(imageView)
                .load(artist.logoUrl)
                .into(imageView)
    }
}