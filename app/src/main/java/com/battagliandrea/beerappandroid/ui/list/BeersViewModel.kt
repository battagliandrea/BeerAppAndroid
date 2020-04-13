package com.battagliandrea.beerappandroid.ui.list


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.battagliandrea.beerappandroid.ui.models.BeerItemUI
import com.battagliandrea.beerappandroid.ui.models.transform
import com.battagliandrea.usecase.GetBeers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class BeersViewModel @Inject constructor(
        private val getBeers: GetBeers
) : ViewModel() {

    val beers = MutableLiveData<List<BeerItemUI>>()

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    init {
        load()
    }

    private fun load(){
        GlobalScope.launch (Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { getBeers() }
            beers.postValue(result.transform())
        }
    }
}
