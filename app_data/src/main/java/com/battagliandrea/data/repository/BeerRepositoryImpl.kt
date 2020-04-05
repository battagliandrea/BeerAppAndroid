package com.battagliandrea.data.repository

import com.battagliandrea.data.datasource.PunkApiDataSource
import com.battagliandrea.domain.entity.BeerEntity
import com.battagliandrea.domain.repository.BeerRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class BeerRepositoryImpl @Inject constructor(
        private val punkApiDataSource: PunkApiDataSource
) : BeerRepository {

    override suspend fun get(): List<BeerEntity> {
       return punkApiDataSource.getBeers()
    }

    override suspend fun get(id: Long): BeerEntity {
        return punkApiDataSource.getBeer(id)
    }
}
