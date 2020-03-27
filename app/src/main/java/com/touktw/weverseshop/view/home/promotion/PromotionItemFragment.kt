package com.touktw.weverseshop.view.home.promotion

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.touktw.weverseshop.BaseFragment
import com.touktw.weverseshop.R
import com.touktw.weverseshop.model.PromotionDto
import kotlinx.android.synthetic.main.fragment_promotion_item.*

/**
 * Created by taekim on 2020-03-26
 */


class PromotionItemFragment : BaseFragment() {
    private var title: String? = null
    private var description: String? = null
    private var imageUrl: String? = null
    override val layoutRes: Int
        get() = R.layout.fragment_promotion_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(KEY_TITLE)
            description = it.getString(KEY_DESCRIPTION)
            imageUrl = it.getString(KEY_IMAGE_URL)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageUrl?.let {
            Glide.with(imageProduct)
                    .load(imageUrl)
                    .centerCrop()
                    .into(imageProduct)
        }
        textTitle.text = title
        textDescription.text = description
    }

    companion object {
        private const val KEY_TITLE = "TITLE"
        private const val KEY_DESCRIPTION = "DESCRIPTION"
        private const val KEY_IMAGE_URL = "IMAGE_URL"

        fun newInstance(promotionDto: PromotionDto): PromotionItemFragment {
            return PromotionItemFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TITLE, promotionDto.title)
                    putString(KEY_DESCRIPTION, promotionDto.description)
                    putString(KEY_IMAGE_URL, promotionDto.imageUrl)
                }
            }
        }
    }
}