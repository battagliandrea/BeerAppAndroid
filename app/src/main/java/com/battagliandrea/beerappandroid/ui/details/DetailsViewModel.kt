package com.battagliandrea.beerappandroid.ui.details

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battagliandrea.beerappandroid.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.beerappandroid.ui.base.ViewState
import com.battagliandrea.domain.entity.BeerEntity
import com.battagliandrea.usecase.GetBeer
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class DetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getBeer: GetBeer
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<DetailsViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): DetailsViewModel
    }

    val beerVS = MutableLiveData<ViewState<BeerEntity>>()

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun load(id: Long){
        viewModelScope.launch {
            try{
                withContext(Dispatchers.Default) { getBeer(id = id) }
                    .let{ beer ->
                        beerVS.postValue(ViewState.Success(data = beer))
                    }
            } catch (e: Exception){
                e.printStackTrace()
                beerVS.postValue(ViewState.Error(throwable = e))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
