package com.battagliandrea.beerappandroid.ui

import com.battagliandrea.domain.entity.BeerEntity


sealed class MainView {

    data class Data(
            val beers: List<BeerEntity> = listOf()
    ): MainView()
}