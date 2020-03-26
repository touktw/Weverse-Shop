package com.touktw.weverseshop.view.home.promotion

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R
import kotlinx.android.synthetic.main.fragment_promotion_item.*
import kotlin.random.Random

/**
 * Created by taekim on 2020-03-26
 */


class PromotionItemFragment : BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.fragment_promotion_item

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageProduct.setCardBackgroundColor(Color.argb(255, Random.nextInt(255), Random.nextInt(255), Random.nextInt(255)))
        textTitle.text = "Title"
        textDescription.text = "Description\nDescription\nDescription\nDescription"
    }
}