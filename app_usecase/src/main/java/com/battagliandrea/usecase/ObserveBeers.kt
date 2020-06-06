package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.BeerEntity
import com.battagliandrea.domain.repository.BeerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ObserveBeers @Inject constructor(
        private val beerRepository: BeerRepository
){

    @ExperimentalCoroutinesApi
    suspend operator fun invoke() : Flow<Pair<List<BeerEntity>, Boolean>>{
            return beerRepository.observe()
    }
}


