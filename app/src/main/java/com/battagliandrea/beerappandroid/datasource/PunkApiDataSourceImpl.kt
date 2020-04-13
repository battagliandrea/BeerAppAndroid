package com.battagliandrea.beerappandroid.datasource

import com.battagliandrea.data.datasource.PunkApiDataSource
import com.battagliandrea.data.entity.map
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
                //TODO: check error
                it.body()?.map()!!.first()
            }
    }

    override suspend fun getBeers(): List<BeerEntity> {
        return punkApiContract.getBeers()
            .let {
                it.body()?.map().orEmpty()
            }
    }

}