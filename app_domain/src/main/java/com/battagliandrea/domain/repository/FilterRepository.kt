package com.battagliandrea.domain.repository

import com.battagliandrea.domain.entity.FilterEntity


interface FilterRepository {

    suspend fun get(): List<FilterEntity>
    suspend fun get(id: Long): FilterEntity
}
