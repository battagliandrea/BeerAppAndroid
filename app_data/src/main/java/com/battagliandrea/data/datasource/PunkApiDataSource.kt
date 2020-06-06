package com.battagliandrea.data.datasource

import com.battagliandrea.domain.entity.BeerEntity


interface PunkApiDataSource {

    suspend fun getBeers(page: Int, limit: Int): List<BeerEntity>

    suspend fun getBeer(id: Long): BeerEntity

    suspend fun filterBeers(beerName: String): List<BeerEntity>
}