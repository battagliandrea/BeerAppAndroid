package com.battagliandrea.beerappandroid.ui.items.beer

import com.battagliandrea.beerappandroid.ui.items.base.ListItem
import com.battagliandrea.domain.entity.BeerEntity

data class BeerItem (
    override var id : Long = 0L,
    var name : String = "",
    var tagline : String = "",
    var description : String = "",
    var image: String = ""
) : ListItem()

fun List<BeerEntity>.toItemModels(): List<BeerItem>{
    return this
        .asSequence()
        .filterNotNull()
        .map { it.toItemModel() }
        .toList()
}

fun BeerEntity.toItemModel(): BeerItem {
    return BeerItem(
        id = this.id,
        name = this.name,
        tagline = this.tagline,
        description = this.description,
        image = this.imageUrl
    )
}