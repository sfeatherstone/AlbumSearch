package uk.co.wedgetech.blockchain.ui.Detail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_currency_detail.*
import kotlinx.android.synthetic.main.view_holder_fragment.*

import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.viewmodel.CurrencyListViewModel

private const val ARG_PARAM1 = "viewModelPosition"


class CurrencyDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var startCurrencyId: Int = 0
    private lateinit var viewModel: CurrencyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            startCurrencyId = it.getInt(ARG_PARAM1)
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

        name.text = viewModel.getCurrency(startCurrencyId)?.name


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
