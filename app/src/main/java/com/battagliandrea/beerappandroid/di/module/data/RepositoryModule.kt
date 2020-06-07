package com.battagliandrea.beerappandroid.di.module.data



import com.battagliandrea.data.repository.BeerRepositoryImpl
import com.battagliandrea.data.repository.FilterRepositoryImpl
import com.battagliandrea.domain.repository.BeerRepository
import com.battagliandrea.domain.repository.FilterRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Provides
    @Singleton
    fun provideBeerRepository(ds: BeerRepositoryImpl): BeerRepository = ds

    @Provides
    @Singleton
    fun provideFiltersRepository(ds: FilterRepositoryImpl): FilterRepository = ds
}
