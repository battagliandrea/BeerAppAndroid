package com.battagliandrea.usecase

import com.battagliandrea.domain.repository.BeerRepository
import javax.inject.Inject

class FilterBeers @Inject constructor(
        private val beerRepository: BeerRepository
){

    suspend operator fun invoke(text: String){
        return beerRepository.filter(text= text)
    }
}


