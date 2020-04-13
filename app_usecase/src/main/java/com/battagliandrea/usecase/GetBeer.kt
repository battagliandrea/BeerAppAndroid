package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.BeerEntity
import com.battagliandrea.domain.repository.BeerRepository
import javax.inject.Inject

class GetBeer @Inject constructor(
        private val beerRepository: BeerRepository
){

    suspend operator fun invoke(id: Long) : BeerEntity{
        return beerRepository.get(id)
    }
}


