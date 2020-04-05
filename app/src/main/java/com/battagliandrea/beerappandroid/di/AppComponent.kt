package com.battagliandrea.beerappandroid.di

import android.app.Application
import com.battagliandrea.beerappandroid.di.binding.ActivityBindingModule
import com.battagliandrea.beerappandroid.di.binding.FragmentBindingModule
import com.battagliandrea.beerappandroid.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            ActivityBindingModule::class,
            FragmentBindingModule::class,
            ViewModelModule::class,
            RepositoryModule::class,
            DataSourceModule::class,
            FrameworkModule::class,
            AndroidSupportInjectionModule::class
        ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun frameworkModule(module: FrameworkModule): Builder

        fun build(): AppComponent
    }

}
