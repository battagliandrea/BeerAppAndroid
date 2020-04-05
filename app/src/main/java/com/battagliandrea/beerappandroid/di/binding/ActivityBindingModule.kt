package com.battagliandrea.beerappandroid.di.binding

import com.battagliandrea.beerappandroid.ui.MainActivity
import com.battagliandrea.beerappandroid.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}
