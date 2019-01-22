package uk.co.wedgetech.blockchain.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.view_holder_fragment.*

import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.dagger.injector
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.viewmodel.CurrencyListViewModel

class ViewPagerFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, injector.currencyListViewModelFactory()).get(CurrencyListViewModel::class.java)
    }

    private lateinit var pagerAdapter: DetailsPagerAdapter
    private var initialPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            initialPosition = it.getInt(ARG_CURRENCY_ID)
        }

        return inflater.inflate(R.layout.view_holder_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            pagerAdapter = DetailsPagerAdapter(it.supportFragmentManager)
        }

        val currencyObserver = Observer<List<Currency>> { currencies ->
            if (currencies!=null)    {
                pagerAdapter.setItems(currencies)
                viewpager.adapter = pagerAdapter
                viewpager.currentItem = initialPosition
            }
        }

        viewModel.currencies.observe(this, currencyObserver)
        viewModel.fetchCurrencies()


    }

    companion object {
        private const val ARG_CURRENCY_ID = "CURRENCY_ID"

        @JvmStatic
        fun newInstance(startCurrencyId: Int) =
            ViewPagerFragment().apply {
                arguments = createParams(startCurrencyId)
            }

        fun createParams(startCurrencyId: Int) =
            Bundle().apply {
                putInt(ARG_CURRENCY_ID, startCurrencyId)
            }
    }

}

