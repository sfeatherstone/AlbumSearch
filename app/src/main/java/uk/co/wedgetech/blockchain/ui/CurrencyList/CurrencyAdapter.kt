package uk.co.wedgetech.blockchain.ui.CurrencyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.model.network.CurrencyListingData
import uk.co.wedgetech.blockchain.model.network.CurrencyQuote

class CurrencyAdapter(private val listener: CardViewPressListener) : RecyclerView.Adapter<CurrencyViewHolder>() {

    interface CardViewPressListener {
        fun onClick(currency: Currency, position: Int)
    }

    private var items: List<Currency> = listOf()


    fun setCurrency(currencyList :List<Currency>) {
        items = currencyList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_card, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        return holder.bindHost(items[position], position, listener)
    }

}