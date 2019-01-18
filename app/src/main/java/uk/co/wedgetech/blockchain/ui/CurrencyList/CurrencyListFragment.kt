package uk.co.wedgetech.blockchain.ui.CurrencyList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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

    internal lateinit var currencyAdapter : CurrencyAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(CurrencyListViewModel::class.java)

        //Catch medal data
        val currencyObserver = Observer<List<Currency>> { currencies ->
            if (currencies!=null)    {
                currencyAdapter.setCurrency(currencies)
            }
        }

        viewModel.currencies.observe(this, currencyObserver)
        viewModel.fetchCurrencies()

        val listener = object : CurrencyAdapter.CardViewPressListener {
            override fun onClick(currency: Currency, position: Int) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ViewPagerFragment.newInstance(position), "MAIN")
                    ?.commitNow()
            }
        }
        currencyAdapter = CurrencyAdapter(listener)

        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.adapter = currencyAdapter
    }

}
