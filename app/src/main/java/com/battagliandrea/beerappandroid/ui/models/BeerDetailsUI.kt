package com.battagliandrea.beerappandroid.ui.models

import android.graphics.Color
import com.battagliandrea.beerappandroid.ui.common.ListItemUI
import com.battagliandrea.domain.entity.BeerEntity

data class BeerDetailsUI (
    override var id : Long = 0L,
    var name : String = String(),
    var description : String = String(),
    var image: String = String()
) : ListItemUI()

fun BeerEntity.toDetailsModel(): BeerDetailsUI{
    return BeerDetailsUI(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.imageUrl
    )
}