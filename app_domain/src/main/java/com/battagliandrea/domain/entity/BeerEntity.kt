package com.battagliandrea.domain.entity

data class BeerEntity (
    var id : Long = 0L,
    var name : String = "",
    var tagline: String = "",
    var description : String = "",
    var imageUrl: String = ""
)