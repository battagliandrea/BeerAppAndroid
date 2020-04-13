package com.battagliandrea.beerappandroid.ui.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.battagliandrea.beerappandroid.R
import com.battagliandrea.beerappandroid.ext.getViewModel
import com.battagliandrea.beerappandroid.ext.observe
import com.battagliandrea.beerappandroid.ui.common.ListItemUI
import com.battagliandrea.beerappandroid.ui.common.TitleItemUI
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class BeersFragment : Fragment() {

    companion object {
        fun newInstance() = BeersFragment()
    }

    private lateinit var mViewModel: BeersViewModel

    @Inject
    lateinit var mAdapter: BeersAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = getViewModel<BeersViewModel>(viewModelFactory)
        with(mViewModel) {
            observe(beers){ renderBeers(it) }
        }

        setupList()
    }

    private fun setupList(){
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(MarginItemDecorator(
            resources.getDimension(R.dimen.default_half_padding).toInt()))
    }

    private fun renderBeers(data: List<ListItemUI>){
        val dataCopied = data.toMutableList()
        dataCopied.add(0, TitleItemUI(text =  getString(R.string.punk_api)))
        mAdapter.updateList(dataCopied)
    }
}
