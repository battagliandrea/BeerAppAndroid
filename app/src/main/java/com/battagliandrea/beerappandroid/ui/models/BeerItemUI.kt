package com.battagliandrea.beerappandroid.ui.models

import android.graphics.Color
import com.battagliandrea.beerappandroid.ui.common.ListItemUI
import com.battagliandrea.domain.entity.BeerEntity

data class BeerItemUI (
    override var id : Long = 0L,
    var name : String = "",
    var tagline : String = "",
    var description : String = "",
    var image: String = ""
) : ListItemUI()

fun List<BeerEntity>.toItemModels(): List<BeerItemUI>{
    return this
        .asSequence()
        .filterNotNull()
        .map { it.toItemModel() }
        .toList()
}

fun BeerEntity.toItemModel(): BeerItemUI{
    return BeerItemUI(
        id = this.id,
        name = this.name,
        tagline = this.tagline,
        description = this.description,
        image = this.imageUrl
    )
}