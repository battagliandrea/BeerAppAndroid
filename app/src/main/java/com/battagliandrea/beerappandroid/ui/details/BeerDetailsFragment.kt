package com.battagliandrea.beerappandroid.ui.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.battagliandrea.beerappandroid.R
import com.battagliandrea.beerappandroid.ext.getViewModel
import com.battagliandrea.beerappandroid.ext.observe
import com.battagliandrea.beerappandroid.ui.models.BeerDetailsUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_beer_details.*
import javax.inject.Inject

class BeerDetailsFragment : Fragment() {

    private lateinit var mViewModel: BeerDetailsViewModel

    val args: BeerDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_beer_details, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = getViewModel(viewModelFactory)
        with(mViewModel) {
            observe(beer){ renderBeer(it) }
        }


        mViewModel.load(args.beerId)
    }

    private fun renderBeer(model: BeerDetailsUI){
        tvTitle.text = model.name
        tvDescription.text = model.description

        Glide.with(this)
            .load(model.image)
            .apply(RequestOptions()
                .centerInside()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate())
            .into(ivImage)
    }
}
