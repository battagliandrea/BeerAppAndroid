package com.battagliandrea.beerappandroid.ui

import android.os.Bundle
import com.battagliandrea.beerappandroid.R
import dagger.android.support.DaggerAppCompatActivity

class BeersActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
