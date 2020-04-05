package com.battagliandrea.data.datasource

import com.battagliandrea.domain.entity.BeerEntity


interface PunkApiDataSource {

    suspend fun getBeers(): List<BeerEntity>

    suspend fun getBeer(id: Long): BeerEntity
}