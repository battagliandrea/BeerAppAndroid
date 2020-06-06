package com.battagliandrea.beerappandroid.di.module.binding

import com.battagliandrea.beerappandroid.di.scope.FragmentScope
import com.battagliandrea.beerappandroid.ui.details.DetailsFragment
import com.battagliandrea.beerappandroid.ui.list.BeersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun beersFragment(): BeersFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun beerDetailsFragment(): DetailsFragment
}
