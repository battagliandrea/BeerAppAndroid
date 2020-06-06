package com.battagliandrea.beerappandroid.ui.main

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.battagliandrea.beerappandroid.R
import com.battagliandrea.beerappandroid.ui.utils.FontSpan
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.view_collapsing_toolbar.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        layout.setupWithNavController(toolbar, navController, appBarConfiguration)


        setupToolbarTitle()
    }


    private fun setupToolbarTitle(){
        SpannableString(tvToolbar.text).apply {
            setSpan(
                FontSpan(ResourcesCompat.getFont(this@MainActivity, R.font.raleway)),
                0,
                4,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )

            setSpan(
                FontSpan(ResourcesCompat.getFont(this@MainActivity, R.font.raleway_extrabold)),
                4,
                this.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            tvToolbar.text = this
        }
    }
}
