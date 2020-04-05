package com.battagliandrea.beerappandroid.datasource

import com.battagliandrea.data.entity.BeerData
import retrofit2.Response
import retrofit2.http.*

interface PunkApiContract {

    @GET("v2/beers/{id}")
    suspend fun getBeer(@Path("uid") id: Long): Response<BeerData>

    @GET("v2/beers")
    suspend fun getBeers(): Response<List<BeerData>>

}