package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.FilterEntity
import com.battagliandrea.domain.repository.FilterRepository
import javax.inject.Inject

class GetFilters @Inject constructor(
        private val filterRepository: FilterRepository
){

    suspend operator fun invoke() : List<FilterEntity>{
        return filterRepository.get()
    }
}


