package com.battagliandrea.beerappandroid.di.module.data


import com.battagliandrea.beerappandroid.datasource.PunkApiDataSourceImpl
import com.battagliandrea.data.datasource.PunkApiDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DataSourceModule {

    @Provides
    @Singleton
    fun providePunkApiDataSource(ds: PunkApiDataSourceImpl): PunkApiDataSource = ds

}
