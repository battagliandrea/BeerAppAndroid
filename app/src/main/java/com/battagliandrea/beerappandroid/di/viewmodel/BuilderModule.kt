package com.battagliandrea.beerappandroid.di.viewmodel

import androidx.lifecycle.ViewModel
import com.abdroid.jrv.core.android.dagger.ViewModelKey
import com.battagliandrea.beerappandroid.ui.details.DetailsViewModel
import com.battagliandrea.beerappandroid.ui.list.MainViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@AssistedModule
@Module(includes=[AssistedInject_BuilderModule::class])
abstract class BuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModelFactory(f: MainViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModelFactory(f: DetailsViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}