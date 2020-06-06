package com.battagliandrea.usecase

import com.battagliandrea.domain.repository.BeerRepository
import javax.inject.Inject

class SyncBeers @Inject constructor(
        private val beerRepository: BeerRepository
){

    suspend operator fun invoke(refresh: Boolean) = beerRepository.sync(refresh = refresh)
}


