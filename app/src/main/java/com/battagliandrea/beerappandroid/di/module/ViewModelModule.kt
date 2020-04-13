package com.battagliandrea.beerappandroid.di.module

import androidx.lifecycle.ViewModel
import com.abdroid.jrv.core.android.dagger.ViewModelKey
import com.battagliandrea.beerappandroid.ui.list.BeersViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(BeersViewModel::class)
    fun provideBeersViewModel(viewModel: BeersViewModel): ViewModel = viewModel
}