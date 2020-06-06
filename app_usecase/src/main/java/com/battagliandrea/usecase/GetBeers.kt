package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.BeerEntity
import com.battagliandrea.domain.repository.BeerRepository
import javax.inject.Inject

class GetBeers @Inject constructor(
        private val beerRepository: BeerRepository
){

    suspend operator fun invoke(refresh: Boolean) : Pair<List<BeerEntity>, Boolean>{
        return beerRepository.get(refresh)
    }
}


