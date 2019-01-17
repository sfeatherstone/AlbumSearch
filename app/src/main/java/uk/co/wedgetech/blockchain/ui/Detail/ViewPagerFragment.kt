package uk.co.wedgetech.blockchain.ui.Detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.view_holder_fragment.*

import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.viewmodel.CurrencyListViewModel

private const val ARG_PARAM1 = "param1"

class ViewPagerFragment : Fragment() {

    private lateinit var viewModel: CurrencyListViewModel

    private lateinit var pagerAdapter: DemoCollectionPagerAdapter
    private var initialPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            initialPosition = it.getInt(ARG_PARAM1)
        }

        return inflater.inflate(R.layout.view_holder_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CurrencyListViewModel::class.java)

        activity?.let {
            pagerAdapter = DemoCollectionPagerAdapter(it.supportFragmentManager)
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

        @JvmStatic
        fun newInstance(startCurrencyId: Int) =
            ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, startCurrencyId)
                }
            }
    }

}

class DemoCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private lateinit var currencies : List<Currency>

    fun setItems(items :List<Currency>) {
        this.currencies = items
    }
    override fun getCount(): Int  = currencies.size

    override fun getItem(i: Int): Fragment {
        return CurrencyDetailFragment.newInstance(i)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "OBJECT " + (position + 1)
    }
}
