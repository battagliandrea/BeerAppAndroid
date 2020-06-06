package com.battagliandrea.beerappandroid.ext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.battagliandrea.beerappandroid.di.viewmodel.InjectingSavedStateViewModelFactory


inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(abstractFactory: InjectingSavedStateViewModelFactory, bundle: Bundle?): T {
    return ViewModelProvider(this, abstractFactory.create(this, bundle))[T::class.java]
}