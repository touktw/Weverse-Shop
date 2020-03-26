package com.touktw.weverseshop.view.home.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.touktw.weverseshop.R
import kotlin.random.Random

/**
 * Created by taekim on 2020-03-26
 */


class ProductAdapter : RecyclerView.Adapter<ProductView>() {
    val items = Random.nextInt(6)
    override fun getItemCount(): Int {

        return 5
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductView {
        return ProductView(LayoutInflater.from(parent.context).inflate(R.layout.item_product_home, parent, false))
    }

    override fun onBindViewHolder(holder: ProductView, position: Int) {
        // TODO
    }
}