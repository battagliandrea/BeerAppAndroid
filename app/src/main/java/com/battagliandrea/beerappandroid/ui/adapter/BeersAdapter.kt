package com.battagliandrea.beerappandroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.beerappandroid.R
import com.battagliandrea.beerappandroid.ui.items.ListItemUI
import com.battagliandrea.beerappandroid.ui.items.TitleItemUI
import com.battagliandrea.beerappandroid.ui.items.TitleItemVH
import com.battagliandrea.beerappandroid.ui.items.BeerItemVH
import com.battagliandrea.beerappandroid.ui.list.OnItemClickListener
import com.battagliandrea.beerappandroid.ui.items.BeerItemUI
import java.lang.RuntimeException
import javax.inject.Inject


open class BeersAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_TITLE = 0
        const val TYPE_BEER = 1
    }

    var onItemClickListener: OnItemClickListener? = null

    private var data: MutableList<ListItemUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_TITLE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_title_item, parent, false)
                TitleItemVH(view)
            }
            TYPE_BEER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_beer_item, parent, false)
                BeerItemVH(view)
            }
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_TITLE -> (holder as TitleItemVH).render(data[position] as TitleItemUI)
            TYPE_BEER -> (holder as BeerItemVH).render(data[position] as BeerItemUI, onItemClickListener)
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
            is TitleItemUI -> TYPE_TITLE
            is BeerItemUI -> TYPE_BEER
            else -> -1
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UTILS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun updateList(data: List<ListItemUI>){
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
}