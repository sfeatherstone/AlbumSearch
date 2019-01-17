package uk.co.wedgetech.blockchain.ui.CurrencyList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import uk.co.wedgetech.blockchain.R

class CurrencyListFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyListFragment()
    }

    private lateinit var viewModel: CurrencyListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrencyListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
