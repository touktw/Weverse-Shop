package com.touktw.weverseshop.base.components

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by taekim on 2020-03-26
 */


class MarginDecoration(private val space:Int = 20) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if(parent.getChildAdapterPosition(view) == 0) {

            }
        }
        super.getItemOffsets(outRect, view, parent, state)
    }
}