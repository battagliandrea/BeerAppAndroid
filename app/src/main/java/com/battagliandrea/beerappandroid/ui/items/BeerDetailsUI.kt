package com.battagliandrea.beerappandroid.ui.items

import com.battagliandrea.domain.entity.BeerEntity

data class BeerDetailsUI (
    override var id : Long = 0L,
    var name : String = String(),
    var description : String = String(),
    var image: String = String()
) : ListItemUI()

fun BeerEntity.toDetailsModel(): BeerDetailsUI {
    return BeerDetailsUI(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.imageUrl
    )
}