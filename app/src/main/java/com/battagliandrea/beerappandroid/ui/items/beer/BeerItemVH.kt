package com.battagliandrea.beerappandroid.ui.items.beer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.beerappandroid.ui.adapter.OnItemClickListener
import com.battagliandrea.beerappandroid.ui.items.beer.BeerItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.view_beer_item.view.*

class BeerItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: BeerItem, listener: OnItemClickListener? = null) = with(itemView) {

//        card.transitionName = "${item.id}"

        tvTitle.text = "${item.id}. ${item.name}"
        tvTagline.text = item.tagline
        tvDescription.text = item.description

        Glide.with(this)
            .load(item.image)
            .apply(RequestOptions()
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate())
            .into(ivImage)

        itemView.setOnClickListener {
            listener?.onItemClick(ivImage, item.id)
        }
    }
}