package uk.co.wedgetech.blockchain.ui.CurrencyList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_card.*
import kotlinx.android.extensions.LayoutContainer
import uk.co.wedgetech.blockchain.model.CurrencyListingData

class CurrencyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    internal lateinit var currency: CurrencyListingData

    fun bindHost(currency_: CurrencyListingData, listener: CurrencyAdapter.CardViewPressListener) {
        this.currency = currency_
        name.text = currency.name
        rank.text = currency.rank.toString()
        price.text = currency.quote.price

        //Click handler
        containerView.setOnClickListener {
            listener.onClick(currency)
        }
    }

}