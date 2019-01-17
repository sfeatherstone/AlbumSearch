package uk.co.wedgetech.blockchain.ui.CurrencyList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_card.*
import kotlinx.android.extensions.LayoutContainer
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.model.network.CurrencyListingData

class CurrencyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    internal lateinit var currency: Currency
    internal var position: Int = 0

    fun bindHost(currency_: Currency, pos: Int, listener: CurrencyAdapter.CardViewPressListener) {
        position = pos
        this.currency = currency_
        name.text = currency.name
        rank.text = currency.rank.toString()
        price.text = currency.price

        //Click handler
        containerView.setOnClickListener {
            listener.onClick(currency, position)
        }
    }

}