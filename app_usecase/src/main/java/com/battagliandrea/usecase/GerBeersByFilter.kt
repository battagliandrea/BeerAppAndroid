package com.battagliandrea.usecase

import com.battagliandrea.domain.repository.BeerRepository
import com.battagliandrea.domain.repository.FilterRepository
import javax.inject.Inject

class GerBeersByFilter @Inject constructor(
    private val filterRepository: FilterRepository,
    private val beerRepository: BeerRepository
){

    suspend operator fun invoke(filterId: Long){
        return filterRepository.get(id= filterId)
            .let { filter -> beerRepository.filter(text= filter.value) }
    }
}


