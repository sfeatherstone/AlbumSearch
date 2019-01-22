package uk.co.wedgetech.blockchain.ui.currencylist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_list_fragment.*

import uk.co.wedgetech.blockchain.dagger.injector
import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.ui.detail.ViewPagerFragment
import uk.co.wedgetech.blockchain.viewmodel.CurrencyListViewModel

class CurrencyListFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyListFragment()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this, injector.currencyListViewModelFactory()).get(CurrencyListViewModel::class.java)
    }

    private lateinit var currencyAdapter : CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listener = object : CurrencyAdapter.CardViewPressListener {
            override fun onClick(currency: Currency, position: Int) {
                view?.findNavController()?.navigate(R.id.action_currencyListFragment_to_viewPagerFragment,
                    ViewPagerFragment.createParams(position))
                //Navigation.createNavigateOnClickListener(R.id, null)
/*
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ViewPagerFragment.newInstance(position), "MAIN")
                    ?.commitNow()
*/
            }
        }
        val currencyAdapter = CurrencyAdapter(listener)

        //Catch medal data
        val currencyObserver = Observer<List<Currency>> { currencies ->
            if (currencies!=null)    {
                currencyAdapter.setCurrency(currencies)
            }
        }

        viewModel.currencies.observe(this, currencyObserver)
        viewModel.fetchCurrencies()

        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.adapter = currencyAdapter
    }

}
