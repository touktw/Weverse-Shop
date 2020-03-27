package com.touktw.weverseshop.view.home.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.touktw.weverseshop.R
import com.touktw.weverseshop.model.NoticeDto

class NoticeAdapter(private val items: List<NoticeDto>) : RecyclerView.Adapter<NoticeViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        return NoticeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notice, parent, false))
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.setNotice(items[position])
    }
}