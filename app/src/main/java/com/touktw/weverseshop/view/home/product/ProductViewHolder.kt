package com.touktw.weverseshop.view.home.product

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.touktw.weverseshop.model.ProductDto
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product_home.*
import java.text.DecimalFormat

/**
 * Created by taekim on 2020-03-26
 */


class ProductViewHolder(override val containerView: View, private val symbol: String?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindProduct(productDto: ProductDto) {
        textTitle.text = productDto.title
        textPrice.text = "${symbol}${currencyFormat.format(productDto.price.toInt())}"

        when {
            productDto.isOnly && productDto.isReserve -> {
                textOnly.visibility = View.VISIBLE
                textDot.visibility = View.VISIBLE
                textReserve.visibility = View.VISIBLE
                layoutInfo.visibility = View.VISIBLE
            }
            productDto.isOnly -> {
                textOnly.visibility = View.VISIBLE
                layoutInfo.visibility = View.VISIBLE
            }
            productDto.isReserve -> {
                textReserve.visibility = View.VISIBLE
                layoutInfo.visibility = View.VISIBLE
            }
            else -> layoutInfo.visibility = View.GONE
        }
    }

    companion object {
        val currencyFormat = DecimalFormat("#,###")
    }
}