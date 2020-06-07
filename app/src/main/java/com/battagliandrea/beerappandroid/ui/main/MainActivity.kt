package com.battagliandrea.beerappandroid.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.battagliandrea.beerappandroid.R
import com.battagliandrea.beerappandroid.di.viewmodel.InjectingSavedStateViewModelFactory
import com.battagliandrea.beerappandroid.ext.getViewModel
import com.battagliandrea.beerappandroid.ext.observe
import com.battagliandrea.beerappandroid.ui.base.ViewState
import com.battagliandrea.beerappandroid.ui.utils.FontSpan
import com.battagliandrea.domain.entity.FilterEntity
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.view_collapsing_toolbar.*
import kotlinx.android.synthetic.main.view_search_bar.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var mViewModel: MainViewModel

    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = getViewModel<MainViewModel>(abstractFactory, savedInstanceState)
        with(mViewModel) {
            observe(chipsGroupVS){ renderChips(it) }
        }

        setupToolbar()
        setupSearch()
        setupChipGroup()
    }


    private fun setupToolbar(){
        val layout = findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        layout.setupWithNavController(toolbar, navController, appBarConfiguration)

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

    private fun setupSearch(){
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                chipGroup.clearCheck()
                mViewModel.filter(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun setupChipGroup(){
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            mViewModel.filter(checkedId.toLong())
        }
    }

    private fun renderChips(viewState: ViewState<List<FilterEntity>>){
        when(viewState){
            is ViewState.Success -> {
                viewState.data?.forEach { filter ->
                    val chip = LayoutInflater.from(this@MainActivity).inflate(R.layout.view_chip_item,null) as Chip
                    chip.id = filter.id.toInt()
                    chip.text = filter.value
                    chipGroup.addView(chip)
                }
            }
            is ViewState.Error -> {
                chipGroup.removeAllViews()
            }
            is ViewState.Loading -> { }
        }
    }
}
