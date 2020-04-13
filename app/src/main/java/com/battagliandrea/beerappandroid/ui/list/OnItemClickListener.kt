package com.battagliandrea.beerappandroid.ui.list

import android.view.View


interface OnItemClickListener {
    fun onItemClick(view: View, beerId: Long)
}