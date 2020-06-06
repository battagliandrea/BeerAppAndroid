package com.battagliandrea.beerappandroid.ui

import android.os.Bundle
import android.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.battagliandrea.beerappandroid.R
import com.google.android.material.appbar.MaterialToolbar
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<MaterialToolbar>(R.id.topAppBar)
            .setupWithNavController(navController, appBarConfiguration)
    }
}
