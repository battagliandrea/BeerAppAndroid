package com.battagliandrea.beerappandroid.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
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
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.transition.MaterialContainerTransform
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_beer_details.*
import javax.inject.Inject
import javax.sql.DataSource

class BeerDetailsFragment : Fragment() {

    private lateinit var mViewModel: BeerDetailsViewModel

    val args: BeerDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postponeEnterTransition()

        val transformationEnter: MaterialContainerTransform = MaterialContainerTransform(requireContext()).apply {
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            duration = 1000
            scrimColor = android.R.color.transparent
        }
        sharedElementEnterTransition = transformationEnter

        val transformationReturn: MaterialContainerTransform = MaterialContainerTransform(requireContext()).apply {
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
            duration = 500
            scrimColor = android.R.color.transparent
        }
        sharedElementReturnTransition = transformationReturn
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_beer_details, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.transitionName = "${args.beerId}"

        mViewModel = getViewModel(viewModelFactory)
        mViewModel.load(args.beerId)
        with(mViewModel) {
            observe(beer){ renderBeer(it) }
        }
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
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: com.bumptech.glide.load.DataSource?, isFirstResource: Boolean): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(ivImage)
    }
}
