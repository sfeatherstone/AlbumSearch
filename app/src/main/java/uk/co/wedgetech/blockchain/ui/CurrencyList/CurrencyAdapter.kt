package uk.co.wedgetech.blockchain.ui.CurrencyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.model.CurrencyListingData
import uk.co.wedgetech.blockchain.model.CurrencyQuote

class CurrencyAdapter(private val listener: CardViewPressListener) : RecyclerView.Adapter<CurrencyViewHolder>() {

    interface CardViewPressListener {
        fun onClick(currency: CurrencyListingData)
    }

    private var items: List<CurrencyListingData> = List(0) {
        CurrencyListingData(0,0, "","", "", CurrencyQuote("","","","")) }

    fun setCurrency(medals :List<CurrencyListingData>) {
        items = medals
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_card, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        return holder.bindHost(items[position], listener)
    }

}