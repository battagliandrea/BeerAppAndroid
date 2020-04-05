package com.battagliandrea.domain.repository

import com.battagliandrea.domain.entity.BeerEntity


interface BeerRepository {

    suspend fun get(): List<BeerEntity>
    suspend fun get(id: Long): BeerEntity
}
