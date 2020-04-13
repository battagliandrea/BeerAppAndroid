package com.battagliandrea.beerappandroid.ui.models

import android.graphics.Color
import com.battagliandrea.beerappandroid.ui.common.ListItemUI
import com.battagliandrea.domain.entity.BeerEntity

data class BeerItemUI (
    override var id : Long = 0L,
    var name : String = String(),
    var description : String = String(),
    var image: String = String()
) : ListItemUI()

fun List<BeerEntity>.transform(): List<BeerItemUI>{
    return this
        .asSequence()
        .filterNotNull()
        .map { it.transform() }
        .toList()
}

fun BeerEntity.transform(): BeerItemUI{
    return BeerItemUI(
        id = this.id,
        name = this.name,
        description = this.description,
        image = this.imageUrl
    )
}