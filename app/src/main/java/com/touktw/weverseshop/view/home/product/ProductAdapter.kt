package com.touktw.weverseshop.view.home.product

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.touktw.weverseshop.R
import com.touktw.weverseshop.db.DatabaseManager
import com.touktw.weverseshop.model.ProductDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by taekim on 2020-03-26
 */


class ProductAdapter(private val items: List<ProductDto>, private val symbol: String?) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product_home, parent, false), symbol)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = items[position]
        holder.bindProduct(item)
        holder.itemView.setOnClickListener {
            Log.d(LOG, "onClick item")
            CoroutineScope(Dispatchers.IO).launch {
                DatabaseManager.get(it.context.applicationContext)
                        .productDao()
                        .insert(item.apply { lastSelectTime = System.currentTimeMillis() })
            }
        }
    }

    companion object {
        private const val LOG = "ProductAdapter"
    }
}