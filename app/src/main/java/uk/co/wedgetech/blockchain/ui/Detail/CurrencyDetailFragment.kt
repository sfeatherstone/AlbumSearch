package uk.co.wedgetech.blockchain.ui.Detail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_currency_detail.*
import kotlinx.android.synthetic.main.view_holder_fragment.*

import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.viewmodel.CurrencyListViewModel

private const val ARG_PARAM1 = "viewModelPosition"


class CurrencyDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var currencyPosition: Int = 0
    private lateinit var viewModel: CurrencyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currencyPosition = it.getInt(ARG_PARAM1)
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
        viewModel = ViewModelProviders.of(this).get(CurrencyListViewModel::class.java)

        val currencyObserver = Observer<List<Currency>> { currencies ->
            if (currencies!=null && currencies.size>currencyPosition)    {
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

        viewModel.currencies.observe(this, currencyObserver)
        viewModel.fetchCurrencies()
    }
    companion object {

        @JvmStatic
        fun newInstance(startCurrencyId: Int) =
            CurrencyDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, startCurrencyId)
                }
            }
    }
}
