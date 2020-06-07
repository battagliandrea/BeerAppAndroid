package com.battagliandrea.beerappandroid.ui.main


import androidx.lifecycle.*
import com.battagliandrea.beerappandroid.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.beerappandroid.ui.base.ViewState
import com.battagliandrea.domain.entity.FilterEntity
import com.battagliandrea.usecase.GerBeersByFilter
import com.battagliandrea.usecase.GerBeersByName
import com.battagliandrea.usecase.GetFilters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*

open class MainViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val gerBeersByName: GerBeersByName,
    private val gerBeersByFilter: GerBeersByFilter,
    private val getFilters: GetFilters
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }

    private var searchJob: Job? = null

    private val _chipsGroupVS = MutableLiveData<ViewState<List<FilterEntity>>>()
    val chipsGroupVS: LiveData<ViewState<List<FilterEntity>>> = _chipsGroupVS

    init {
        loadFilters()
    }

    fun filter(text: String){
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            try{
                withContext(Dispatchers.Default) { gerBeersByName(text = text) }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun filter(id: Long){
        viewModelScope.launch {
            try{
                withContext(Dispatchers.Default) { gerBeersByFilter(filterId = id) }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun loadFilters(){
        viewModelScope.launch {
            try{
                withContext(Dispatchers.Default) { getFilters() }
                    .let { _chipsGroupVS.postValue(ViewState.Success(data = it)) }
            } catch (e: Exception){
                e.printStackTrace()
                _chipsGroupVS.postValue(ViewState.Error(throwable = e))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
