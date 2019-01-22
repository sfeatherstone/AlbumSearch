package uk.co.wedgetech.blockchain.util

import android.content.Context
import uk.co.wedgetech.blockchain.R
import javax.inject.Inject
import java.util.*


class BestCurrency @Inject constructor(private var context: Context) {

    private val supportedCurrencies by lazy {
        context.resources.getStringArray(R.array.supported_currencies).toSet()
    }

    fun getCurrency():String {
        val defaultLocale = Locale.getDefault()
        val currency = Currency.getInstance(defaultLocale)
        return if (currency.currencyCode in supportedCurrencies) {
            currency.currencyCode
        }
        else {
            "USD"
        }
    }
}