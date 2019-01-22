package uk.co.wedgetech.blockchain.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_currency_detail.*

import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.dagger.injector
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.viewmodel.CurrencyListViewModel


class CurrencyDetailFragment : Fragment() {
    private var currencyPosition: Int = 0
    private val viewModel by lazy {
        ViewModelProviders.of(this, injector.currencyListViewModelFactory()).get(CurrencyListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currencyPosition = it.getInt(ARG_CURRENCY_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val currencyObserver = Observer<List<Currency>> {
            it?.let { currencies ->
                if (currencies.size>currencyPosition) {
                    //TODO format currency correctly
                    name.text = currencies[currencyPosition].name
                    rank.text = currencies[currencyPosition].rank.toString()
                    price.text = currencies[currencyPosition].price
                    market_cap.text = currencies[currencyPosition].marketCap
                    volume.text = currencies[currencyPosition].volume24h
                    circulating_supply.text = currencies[currencyPosition].circulatingSupply
                    max_supply.text = currencies[currencyPosition].maxSupply
                }
            }

        }

        viewModel.currencies.observe(this, currencyObserver)
        viewModel.fetchCurrencies()
    }
    companion object {
        private const val ARG_CURRENCY_ID = "CURRENCY_ID"

        @JvmStatic
        fun newInstance(startCurrencyId: Int) =
            CurrencyDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CURRENCY_ID, startCurrencyId)
                }
            }
    }
}
