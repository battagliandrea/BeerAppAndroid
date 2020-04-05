package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.BeerEntity
import com.battagliandrea.domain.repository.BeerRepository
import javax.inject.Inject

class GetBeers @Inject constructor(
        private val beerRepository: BeerRepository
){

    suspend operator fun invoke() : List<BeerEntity>{
        return beerRepository.get()
    }
}


