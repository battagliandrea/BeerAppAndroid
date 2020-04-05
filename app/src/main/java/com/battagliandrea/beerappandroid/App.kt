package com.battagliandrea.beerappandroid

import com.battagliandrea.beerappandroid.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


open class App : DaggerApplication() {

    var applicationInjector: AndroidInjector<DaggerApplication>? = null


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        if (applicationInjector == null) {
            applicationInjector = DaggerAppComponent.builder()
                    .application(this)
                    .build()
        }

        return applicationInjector as AndroidInjector<DaggerApplication>
    }
}