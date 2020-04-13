package com.battagliandrea.data.entity

import com.battagliandrea.domain.entity.BeerEntity

data class BeerData (
    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    var image_url: String = ""
)

fun List<BeerData>.map(): List<BeerEntity>{
    return this
        .asSequence()
        .filterNotNull()
        .map { it.map() }
        .toList()
}

fun BeerData.map(): BeerEntity{
    return BeerEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.image_url
    )
}