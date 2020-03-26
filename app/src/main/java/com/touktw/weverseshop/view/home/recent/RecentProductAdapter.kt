package com.touktw.weverseshop.view.home.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.touktw.weverseshop.R
import com.touktw.weverseshop.model.ProductDto

class RecentProductAdapter : RecyclerView.Adapter<RecentProductViewHolder>() {
    val items = mutableListOf<ProductDto>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentProductViewHolder {
        return RecentProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_recent, parent, false))
    }

    override fun onBindViewHolder(holder: RecentProductViewHolder, position: Int) {
        holder.bindProduct(items[position])
    }
}