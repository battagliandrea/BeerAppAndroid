package com.battagliandrea.beerappandroid.di

import android.app.Application
import com.battagliandrea.beerappandroid.di.module.binding.ActivityModule
import com.battagliandrea.beerappandroid.di.module.binding.FragmentModule
import com.battagliandrea.beerappandroid.di.module.*
import com.battagliandrea.beerappandroid.di.module.data.DataSourceModule
import com.battagliandrea.beerappandroid.di.module.data.RepositoryModule
import com.battagliandrea.beerappandroid.di.module.framework.RetrofitModule
import com.battagliandrea.beerappandroid.di.viewmodel.BuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            UtilsModule::class,
            ActivityModule::class,
            FragmentModule::class,
            BuilderModule::class,
            RepositoryModule::class,
            DataSourceModule::class,
            RetrofitModule::class,
            AndroidSupportInjectionModule::class
        ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun retrofitModule(module: RetrofitModule): Builder

        fun build(): AppComponent
    }

}
