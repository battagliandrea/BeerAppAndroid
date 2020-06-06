package com.battagliandrea.beerappandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.beerappandroid.R
import com.battagliandrea.beerappandroid.ui.items.base.ListItem
import com.battagliandrea.beerappandroid.ui.items.beer.BeerItemVH
import com.battagliandrea.beerappandroid.ui.items.beer.BeerItem
import com.battagliandrea.beerappandroid.ui.items.beer.OnBeerClickListener
import com.battagliandrea.beerappandroid.ui.items.loading.LoadingItem
import com.battagliandrea.beerappandroid.ui.items.loading.LoadingItemVH
import java.lang.RuntimeException
import java.util.*
import javax.inject.Inject


open class BeersAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_BEER = 1
        const val TYPE_LOADER = 2
    }

    var onBeerClickListener: OnBeerClickListener? = null

    private var data: MutableList<ListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_BEER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_beer_item, parent, false)
                BeerItemVH(view)
            }
            TYPE_LOADER-> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_loading_item, parent, false)
                LoadingItemVH(view)
            }
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_BEER -> (holder as BeerItemVH).render(data[position] as BeerItem, onBeerClickListener)
            TYPE_LOADER -> (holder as LoadingItemVH).render(data[position] as LoadingItem)
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is BeerItem -> TYPE_BEER
            is LoadingItem -> TYPE_LOADER
            else -> -1
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UTILS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun updateList(data: List<ListItem>){
        if(this.data != data){
            val diffCallback =
                BeersDiffUtils(
                    this.data,
                    data
                )
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            this.data.clear()
            this.data.addAll(data)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    fun showLoadingItem(){
        this.data.add(this.data.size, LoadingItem(id = UUID.randomUUID().hashCode().toLong()))
        notifyItemChanged(this.data.size)
    }
}