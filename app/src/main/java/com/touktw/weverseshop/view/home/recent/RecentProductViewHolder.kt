package com.touktw.weverseshop.view.home.recent

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.touktw.weverseshop.model.ProductDto
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product_recent.*

class RecentProductViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindProduct(productDto: ProductDto) {
        textTitle.text = productDto.title
    }
}