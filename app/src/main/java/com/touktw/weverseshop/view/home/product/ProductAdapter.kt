package com.touktw.weverseshop.view.home.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.touktw.weverseshop.R
import com.touktw.weverseshop.model.ProductDto
import kotlin.random.Random

/**
 * Created by taekim on 2020-03-26
 */


class ProductAdapter(private val items: List<ProductDto>) : RecyclerView.Adapter<ProductView>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductView {
        return ProductView(LayoutInflater.from(parent.context).inflate(R.layout.item_product_home, parent, false))
    }

    override fun onBindViewHolder(holder: ProductView, position: Int) {
        holder.bindProduct(items[position])
    }
}