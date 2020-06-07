package com.battagliandrea.data.repository

import com.battagliandrea.domain.entity.FilterEntity
import com.battagliandrea.domain.repository.FilterRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class FilterRepositoryImpl @Inject constructor(
) : FilterRepository {

    private val filters = listOf(
        FilterEntity(id = 0, value = "Blonde"),
        FilterEntity(id = 1, value = "Lager"),
        FilterEntity(id = 2, value = "IPA"),
        FilterEntity(id = 3, value = "Dark"),
        FilterEntity(id = 4, value = "Pale Ale"),
        FilterEntity(id = 5, value = "AB")
    )

    override suspend fun get(): List<FilterEntity> {
        return filters
    }

    override suspend fun get(id: Long): FilterEntity {
        return filters.first { it.id == id }
    }
}
