package com.battagliandrea.domain.repository

import com.battagliandrea.domain.entity.BeerEntity


interface BeerRepository {

    suspend fun get(refresh: Boolean): Pair<List<BeerEntity>, Boolean>
    suspend fun get(id: Long): BeerEntity
}
