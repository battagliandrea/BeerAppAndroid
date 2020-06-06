package com.battagliandrea.beerappandroid.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.battagliandrea.beerappandroid.ui.items.base.ListItem
import com.battagliandrea.beerappandroid.ui.items.beer.BeerItem

class  BeersDiffUtils(
    private val oldBeers: List<ListItem>,
    private val newBeers: List<ListItem>
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
                && oldBeer is BeerItem
                && newBeer is BeerItem
                && oldBeer.name == newBeer.name
                && oldBeer.description == newBeer.description
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}