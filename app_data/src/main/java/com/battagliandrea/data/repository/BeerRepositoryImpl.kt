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

    private val LIMIT_SIZE = 25

    private var currentPage: Int = 1
    private var isLastPage: Boolean = false
    private val beersCache: MutableList<BeerEntity> = mutableListOf()

    override suspend fun get(refresh: Boolean): Pair<List<BeerEntity>, Boolean> {
        if(refresh){
            currentPage = 1
            beersCache.clear()
        }

        return punkApiDataSource.getBeers(page = currentPage, limit = LIMIT_SIZE)
            .let { remoteData ->
                beersCache.addAll(remoteData)

                currentPage += 1
                isLastPage = remoteData.size < LIMIT_SIZE

                beersCache to isLastPage
            }
    }

    override suspend fun get(id: Long): BeerEntity {
        return punkApiDataSource.getBeer(id)
    }
}
