package com.battagliandrea.beerappandroid.di.module

import com.battagliandrea.data.repository.BeerRepositoryImpl
import com.battagliandrea.domain.repository.BeerRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Provides
    @Singleton
    fun provideBeerRepository(r: BeerRepositoryImpl): BeerRepository = r


}
