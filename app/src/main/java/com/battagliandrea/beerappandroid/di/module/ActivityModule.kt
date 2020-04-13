package com.battagliandrea.beerappandroid.di.module

import com.battagliandrea.beerappandroid.ui.BeersActivity
import com.battagliandrea.beerappandroid.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun beersActivity(): BeersActivity

}
