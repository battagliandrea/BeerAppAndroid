package com.battagliandrea.beerappandroid.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.battagliandrea.usecase.GetBeers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class MainViewModel @Inject constructor(

        private val getBeers: GetBeers

) : ViewModel() {

    private val _data = MutableLiveData<MainView.Data>()
    val data: MutableLiveData<MainView.Data> get() = _data

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    init {
        load()
    }


    private fun load(){
        GlobalScope.launch (Dispatchers.Main) {
            val beers = withContext(Dispatchers.IO) { getBeers() }
            data.value = MainView.Data(beers)
        }
    }
}
