package com.battagliandrea.beerappandroid.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.battagliandrea.beerappandroid.ui.common.ListItemUI
import com.battagliandrea.beerappandroid.ui.models.BeerItemUI

class  BeersDiffUtils(
        private val oldBeers: List<ListItemUI>,
        private val newBeers: List<ListItemUI>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldBeers.size

    override fun getNewListSize(): Int = newBeers.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBeers[oldItemPosition].hashCode() == newBeers[newItemPosition].hashCode()
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val oldBeer = oldBeers[oldPosition]
        val newBeer = newBeers[newPosition]
        return oldBeer.id == newBeer.id
                && oldBeer is BeerItemUI
                && newBeer is BeerItemUI
                && oldBeer.name == newBeer.name
                && oldBeer.description == newBeer.description
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}