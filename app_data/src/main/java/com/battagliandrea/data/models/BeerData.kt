package com.battagliandrea.data.models


import com.battagliandrea.domain.entity.BeerEntity
import com.google.gson.annotations.SerializedName

data class BeerData(
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("tagline")
    val tagline: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("image_url")
    val imageUrl: String? = ""
)

fun List<BeerData>.map(): List<BeerEntity>{
    return this
        .asSequence()
        .filterNotNull()
        .map { it.map() }
        .toList()
}

fun BeerData.map(): BeerEntity {
    return BeerEntity(
        id = this.id,
        name = this.name.orEmpty(),
        tagline = this.tagline.orEmpty(),
        description = this.description.orEmpty(),
        imageUrl = this.imageUrl.orEmpty()
    )
}