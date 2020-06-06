package com.battagliandrea.beerappandroid.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.battagliandrea.beerappandroid.R
import com.battagliandrea.beerappandroid.di.viewmodel.InjectingSavedStateViewModelFactory
import com.battagliandrea.beerappandroid.ext.getViewModel
import com.battagliandrea.beerappandroid.ext.observe
import com.battagliandrea.beerappandroid.ui.base.ViewState
import com.battagliandrea.domain.entity.BeerEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_beer_details.*
import javax.inject.Inject

class DetailsFragment : BottomSheetDialogFragment() {

    private lateinit var mViewModel: DetailsViewModel

    val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_beer_details, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivImage.transitionName = "${args.beerId}"

        mViewModel = getViewModel<DetailsViewModel>(abstractFactory, savedInstanceState)
        mViewModel.load(args.beerId)
        with(mViewModel) {
            observe(beerVS){ renderBeer(it) }
        }
    }

    private fun renderBeer(viewState: ViewState<BeerEntity>){
        when(viewState){
            is ViewState.Success -> {

                val model = viewState.data

                tvTitle.text = "${model?.id}. ${model?.name}"
                tvDescription.text = model?.description
                tvTagline.text = model?.tagline

                Glide.with(this)
                    .load(model?.imageUrl)
                    .apply(RequestOptions()
                        .centerInside()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .dontAnimate())
                    .into(ivImage)
            }
            is ViewState.Error -> {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }


    }
}
