package com.touktw.weverseshop.view.home.notice

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.touktw.weverseshop.model.NoticeDto
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_notice.*
import java.text.SimpleDateFormat
import java.util.*


class NoticeViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    private val format = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())

    fun setNotice(noticeDto: NoticeDto) {
        textNotice.text = noticeDto.notice
        textDate.text = format.format(noticeDto.createAt)
    }
}