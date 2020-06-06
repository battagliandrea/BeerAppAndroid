package com.battagliandrea.data.models


import com.battagliandrea.domain.entity.BeerEntity
import com.google.gson.annotations.SerializedName

data class BeerData(
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("first_brewed")
    val firstBrewed: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("image_url")
    val imageUrl: String = "",
    @SerializedName("abv")
    val abv: Double = 0.0,
    @SerializedName("ibu")
    val ibu: Double = 0.0,
    @SerializedName("target_fg")
    val targetFg: Double = 0.0,
    @SerializedName("target_og")
    val targetOg: Double = 0.0,
    @SerializedName("ebc")
    val ebc: Double = 0.0,
    @SerializedName("srm")
    val srm: Double = 0.0,
    @SerializedName("ph")
    val ph: Double = 0.0,
    @SerializedName("attenuation_level")
    val attenuationLevel: Double = 0.0,
    @SerializedName("volume")
    val volume: Volume = Volume(),
    @SerializedName("boil_volume")
    val boilVolume: BoilVolume = BoilVolume(),
    @SerializedName("method")
    val method: Method = Method(),
    @SerializedName("ingredients")
    val ingredients: Ingredients = Ingredients(),
    @SerializedName("food_pairing")
    val foodPairing: List<String> = listOf(),
    @SerializedName("brewers_tips")
    val brewersTips: String = "",
    @SerializedName("contributed_by")
    val contributedBy: String = ""
) {
    data class Volume(
        @SerializedName("value")
        val value: Int = 0,
        @SerializedName("unit")
        val unit: String = ""
    )

    data class BoilVolume(
        @SerializedName("value")
        val value: Int = 0,
        @SerializedName("unit")
        val unit: String = ""
    )

    data class Method(
        @SerializedName("mash_temp")
        val mashTemp: List<MashTemp> = listOf(),
        @SerializedName("fermentation")
        val fermentation: Fermentation = Fermentation(),
        @SerializedName("twist")
        val twist: String = ""
    ) {
        data class MashTemp(
            @SerializedName("temp")
            val temp: Temp = Temp(),
            @SerializedName("duration")
            val duration: Int = 0
        ) {
            data class Temp(
                @SerializedName("value")
                val value: Int = 0,
                @SerializedName("unit")
                val unit: String = ""
            )
        }

        data class Fermentation(
            @SerializedName("temp")
            val temp: Temp = Temp()
        ) {
            data class Temp(
                @SerializedName("value")
                val value: Int = 0,
                @SerializedName("unit")
                val unit: String = ""
            )
        }
    }

    data class Ingredients(
        @SerializedName("malt")
        val malt: List<Malt> = listOf(),
        @SerializedName("hops")
        val hops: List<Hop> = listOf(),
        @SerializedName("yeast")
        val yeast: String = ""
    ) {
        data class Malt(
            @SerializedName("name")
            val name: String = "",
            @SerializedName("amount")
            val amount: Amount = Amount()
        ) {
            data class Amount(
                @SerializedName("value")
                val value: Double = 0.0,
                @SerializedName("unit")
                val unit: String = ""
            )
        }

        data class Hop(
            @SerializedName("name")
            val name: String = "",
            @SerializedName("amount")
            val amount: Amount = Amount(),
            @SerializedName("add")
            val add: String = "",
            @SerializedName("attribute")
            val attribute: String = ""
        ) {
            data class Amount(
                @SerializedName("value")
                val value: Double = 0.0,
                @SerializedName("unit")
                val unit: String = ""
            )
        }
    }
}

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
        name = this.name,
        tagline = this.tagline,
        description = this.description,
        imageUrl = this.imageUrl
    )
}