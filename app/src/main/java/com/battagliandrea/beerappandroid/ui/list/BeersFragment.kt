package com.battagliandrea.beerappandroid.ui.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import com.battagliandrea.beerappandroid.R
import com.battagliandrea.beerappandroid.di.viewmodel.InjectingSavedStateViewModelFactory
import com.battagliandrea.beerappandroid.ext.getViewModel
import com.battagliandrea.beerappandroid.ext.observe
import com.battagliandrea.beerappandroid.ui.adapter.BeersAdapter
import com.battagliandrea.beerappandroid.ui.adapter.OnItemClickListener
import com.battagliandrea.beerappandroid.ui.base.ViewState
import com.battagliandrea.beerappandroid.ui.items.base.ListItem
import com.battagliandrea.beerappandroid.ui.utils.MarginItemDecorator
import com.battagliandrea.beerappandroid.ui.utils.PaginationListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_beers.*
import javax.inject.Inject

class BeersFragment : Fragment() {

    private lateinit var mViewModel: BeersViewModel

    @Inject
    lateinit var mAdapter: BeersAdapter

    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        exitTransition = Hold()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_beers, container, false)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = getViewModel<BeersViewModel>(abstractFactory, savedInstanceState)
        with(mViewModel) {
            observe(beersListVS){ renderBeers(it) }
        }

        setupList()
    }

    private fun setupList(){
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(MarginItemDecorator(resources.getDimension(R.dimen.default_padding).toInt()))

        val lm = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : PaginationListener(lm) {
            override fun loadMoreItems() {
                mViewModel.load()
            }

            override fun isLastPage(): Boolean {
                return mViewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return mViewModel.isLoading
            }
        })

        mAdapter.onItemClickListener = object :
            OnItemClickListener {
            override fun onItemClick(view: View, beerId: Long) {

//                val action = BeersFragmentDirections.actionBeersFragmentToBeerDetailsFragment(beerId)
//                val extras: FragmentNavigator.Extras = FragmentNavigatorExtras(view to "${beerId}")

//                findNavController().navigate(action)
            }
        }
    }


    private fun renderBeers(viewState: ViewState<List<ListItem>>){
        when(viewState){
            is ViewState.Success -> {
                mAdapter.updateList(data = viewState.data.orEmpty())
            }
            is ViewState.Error -> {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }
            is ViewState.Loading -> {
                mAdapter.showLoadingItem()
            }
        }
    }

}
