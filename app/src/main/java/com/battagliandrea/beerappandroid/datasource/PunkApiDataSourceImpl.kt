package com.battagliandrea.beerappandroid.datasource

import com.battagliandrea.data.datasource.PunkApiDataSource
import com.battagliandrea.data.models.map
import com.battagliandrea.domain.entity.BeerEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PunkApiDataSourceImpl @Inject constructor(
    private val punkApiContract: PunkApiContract
) : PunkApiDataSource {

    override suspend fun getBeer(id: Long): BeerEntity {
        return punkApiContract.getBeer(id)
            .let {
                it.body()?.map()!!.first()
            }
    }

    override suspend fun getBeers(page: Int, limit: Int): List<BeerEntity> {
        return punkApiContract.getBeers(page = page, limit = limit)
            .let {
                it.body()?.map().orEmpty()
            }
    }

    override suspend fun filterBeers(beerName: String): List<BeerEntity> {
        return punkApiContract.filterBeers(beerName = beerName)
            .let {
                it.body()?.map().orEmpty()
            }
    }
}