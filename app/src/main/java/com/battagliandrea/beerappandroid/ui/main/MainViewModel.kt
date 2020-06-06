package com.battagliandrea.beerappandroid.ui.main


import androidx.lifecycle.*
import com.battagliandrea.beerappandroid.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.beerappandroid.ui.base.ViewState
import com.battagliandrea.beerappandroid.ui.items.ListItemUI
import com.battagliandrea.beerappandroid.ui.items.toItemModels
import com.battagliandrea.usecase.GetBeers
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class MainViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getBeers: GetBeers
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    private val _beersListVS = MutableLiveData<ViewState<List<ListItemUI>>>()
    val beersListVS: LiveData<ViewState<List<ListItemUI>>> = _beersListVS

    init {
        load()
    }

    fun load(){
        viewModelScope.launch {
            try{

                isLoading = true
                _beersListVS.postValue(ViewState.Loading())

                val (beers, isLastPage) = withContext(Dispatchers.Default) { getBeers(refresh = false) }
                val data = beers.toItemModels()

                this@MainViewModel.isLastPage = isLastPage
                this@MainViewModel.isLoading = false
                _beersListVS.postValue(ViewState.Success(data = data))

            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
