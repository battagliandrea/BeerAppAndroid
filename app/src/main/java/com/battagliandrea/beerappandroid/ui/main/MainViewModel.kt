package com.battagliandrea.beerappandroid.ui.main


import androidx.lifecycle.*
import com.battagliandrea.beerappandroid.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.beerappandroid.ui.base.ViewState
import com.battagliandrea.beerappandroid.ui.items.base.ListItem
import com.battagliandrea.beerappandroid.ui.items.beer.toItemModels
import com.battagliandrea.usecase.FilterBeers
import com.battagliandrea.usecase.ObserveBeers
import com.battagliandrea.usecase.SyncBeers
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.lang.RuntimeException

open class MainViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val filterBeers: FilterBeers
) : ViewModel() {

    private var searchJob: Job? = null

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }

    fun filter(text: String){

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try{
                withContext(Dispatchers.Default) { filterBeers(text = text) }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
