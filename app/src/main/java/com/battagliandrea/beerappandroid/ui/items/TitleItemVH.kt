package com.battagliandrea.beerappandroid.ui.items

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.beerappandroid.ui.items.TitleItemUI
import kotlinx.android.synthetic.main.view_title_item.view.*

class TitleItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: TitleItemUI) = with(itemView) {
        tvTitle.text = item.text
    }
}