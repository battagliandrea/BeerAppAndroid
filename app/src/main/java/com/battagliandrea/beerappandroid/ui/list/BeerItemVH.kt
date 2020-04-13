package com.battagliandrea.beerappandroid.ui.list

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.beerappandroid.R
import com.battagliandrea.beerappandroid.ui.models.BeerItemUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.view_beer_item.view.*

class BeerItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: BeerItemUI) = with(itemView) {
        tvTitle.text = item.name
        tvDescription.text = item.description

        if(item.image.isNotEmpty()){
            ivImage.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
        } else {
            ivImage.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorBackground))
        }

        Glide.with(this)
            .load(item.image)
            .apply(RequestOptions()
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate())
            .into(ivImage)
    }
}