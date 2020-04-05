package com.battagliandrea.beerappandroid.di.module

import com.battagliandrea.beerappandroid.datasource.PunkApiDataSourceImpl
import com.battagliandrea.data.datasource.PunkApiDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provide steps-level dependencies.
 */
@Module
open class DataSourceModule {

    @Provides
    @Singleton
    fun provideAwsDataSource(ds: PunkApiDataSourceImpl): PunkApiDataSource = ds

}
