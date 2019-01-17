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

import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.model.CurrencyListingData
import uk.co.wedgetech.blockchain.viewmodel.CurrencyListViewModel

class CurrencyListFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyListFragment()
    }

    private lateinit var viewModel: CurrencyListViewModel
    internal lateinit var currencyAdapter : CurrencyAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrencyListViewModel::class.java)

        //Catch medal data
        val currencyObserver = Observer<List<CurrencyListingData>> { currencies ->
            if (currencies!=null)    {
                currencyAdapter.setCurrency(currencies)
            }
        }

        viewModel.currencies.observe(this, currencyObserver)
        viewModel.fetchCurrencies()

        val listener = object : CurrencyAdapter.CardViewPressListener {
            override fun onClick(currency: CurrencyListingData) {
                //TODO
            }
        }
        currencyAdapter = CurrencyAdapter(listener)

        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.adapter = currencyAdapter
    }

}
