package com.battagliandrea.beerappandroid.ui.list


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.battagliandrea.beerappandroid.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.beerappandroid.ui.items.BeerItemUI
import com.battagliandrea.beerappandroid.ui.items.toItemModels
import com.battagliandrea.usecase.GetBeers
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class MainViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getBeers: GetBeers
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }


    val beers = MutableLiveData<List<BeerItemUI>>()

    init {
        load()
    }

    private fun load(){
        GlobalScope.launch (Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { getBeers() }
            beers.postValue(result.toItemModels())
        }
    }
}
