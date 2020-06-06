package com.battagliandrea.beerappandroid.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.battagliandrea.beerappandroid.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.usecase.GetBeer
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

open class DetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val getBeer: GetBeer
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<DetailsViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): DetailsViewModel
    }

//    val beer = MutableLiveData<BeerDetailsUI>()

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    fun load(id: Long){
//        GlobalScope.launch (Dispatchers.Main) {
//            val result = withContext(Dispatchers.IO) { getBeer(id) }
//            beer.postValue(result.toDetailsModel())
//        }
//    }
}
