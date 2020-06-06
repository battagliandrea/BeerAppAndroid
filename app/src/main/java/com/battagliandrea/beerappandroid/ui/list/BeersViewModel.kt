package com.battagliandrea.beerappandroid.ui.list


import androidx.lifecycle.*
import com.battagliandrea.beerappandroid.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.beerappandroid.ui.base.ViewState
import com.battagliandrea.beerappandroid.ui.items.base.ListItem
import com.battagliandrea.beerappandroid.ui.items.beer.toItemModels
import com.battagliandrea.usecase.ObserveBeers
import com.battagliandrea.usecase.SyncBeers
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.lang.RuntimeException

open class BeersViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val syncBeers: SyncBeers,
    private val observeBeers: ObserveBeers
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<BeersViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): BeersViewModel
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    private val _beersListVS = MutableLiveData<ViewState<List<ListItem>>>()
    val beersListVS: LiveData<ViewState<List<ListItem>>> = _beersListVS

    init {
        observer()
        load()
    }

    @ExperimentalCoroutinesApi
    private fun observer(){
        viewModelScope.launch {
            try {

                observeBeers().collect { (beers, isLastPage) ->

                    val data = beers.toItemModels()
                    _beersListVS.postValue(ViewState.Success(data = data))

                    this@BeersViewModel.isLastPage = isLastPage
                    this@BeersViewModel.isLoading = false
                }

            } catch (e: java.lang.Exception){
                _beersListVS.postValue(ViewState.Error(data = emptyList(), throwable= RuntimeException("Error")))
            }
        }
    }

    fun load(){
        viewModelScope.launch {
            try{
                this@BeersViewModel.isLoading = true
                _beersListVS.postValue(ViewState.Loading())

                withContext(Dispatchers.Default) { syncBeers(refresh = false) }

            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
