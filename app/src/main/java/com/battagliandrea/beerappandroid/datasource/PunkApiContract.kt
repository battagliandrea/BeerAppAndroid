package com.battagliandrea.beerappandroid.datasource

import com.battagliandrea.data.models.BeerData
import retrofit2.Response
import retrofit2.http.*

interface PunkApiContract {

    @GET("v2/beers/{id}")
    suspend fun getBeer(@Path("id") id: Long): Response<List<BeerData>>

    @GET("v2/beers")
    suspend fun getBeers(@Query("page") page: Int, @Query("per_page") limit: Int): Response<List<BeerData>>

    @GET("v2/beers")
    suspend fun filterBeers(@Query("beer_name") beerName: String): Response<List<BeerData>>

}