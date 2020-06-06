package com.battagliandrea.domain.repository

import com.battagliandrea.domain.entity.BeerEntity
import kotlinx.coroutines.flow.Flow


interface BeerRepository {

    suspend fun observe(): Flow<Pair<List<BeerEntity>, Boolean>>
    suspend fun sync(refresh: Boolean)

    suspend fun filter(text: String)

    suspend fun get(id: Long): BeerEntity
}
