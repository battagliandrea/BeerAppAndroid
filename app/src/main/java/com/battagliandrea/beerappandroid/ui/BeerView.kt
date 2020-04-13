package com.battagliandrea.beerappandroid.ui

import com.battagliandrea.domain.entity.BeerEntity


sealed class BeerView {

    object Idle
    object Loading

    data class ListView(
            val beers: List<BeerEntity> = listOf()
    ): BeerView()
}