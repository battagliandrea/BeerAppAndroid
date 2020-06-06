package com.battagliandrea.beerappandroid.ui.adapter

import android.view.View


interface OnItemClickListener {
    fun onItemClick(view: View, beerId: Long)
}