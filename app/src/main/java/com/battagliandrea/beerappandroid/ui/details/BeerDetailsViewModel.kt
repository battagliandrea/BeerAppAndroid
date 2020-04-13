package com.battagliandrea.beerappandroid.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.battagliandrea.beerappandroid.ui.models.BeerDetailsUI
import com.battagliandrea.beerappandroid.ui.models.toDetailsModel
import com.battagliandrea.usecase.GetBeer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class BeerDetailsViewModel @Inject constructor(
        private val getBeer: GetBeer
) : ViewModel() {

    val beer = MutableLiveData<BeerDetailsUI>()

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun load(id: Long){
        GlobalScope.launch (Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { getBeer(id) }
            beer.postValue(result.toDetailsModel())
        }
    }
}
