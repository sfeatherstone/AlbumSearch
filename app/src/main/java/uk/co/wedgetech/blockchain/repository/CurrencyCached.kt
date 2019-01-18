package uk.co.wedgetech.blockchain.repository

import android.util.Log
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import uk.co.wedgetech.blockchain.BuildConfig
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.model.network.CurrencyListingPayload
import uk.co.wedgetech.blockchain.network.CurrencyAPI
import uk.co.wedgetech.blockchain.util.BestCurrency
import javax.inject.Inject

//Convert from network format to UI format model class
private fun convert(value: CurrencyListingPayload):Single<List<Currency>> {
    val tempCurrencyList = mutableListOf<Currency>()
    value.data?.let {
        for (currencyNetwork in it) {
            tempCurrencyList.add(
                Currency(
                    currencyNetwork.id,
                    currencyNetwork.rank,
                    currencyNetwork.name,
                    currencyNetwork.circulatingSupply,
                    currencyNetwork.maxSupply,
                    currencyNetwork.quote.currency,
                    currencyNetwork.quote.price,
                    currencyNetwork.quote.volume24h,
                    currencyNetwork.quote.marketCap
                )
            )
        }
        return Single.just(tempCurrencyList)
    }
}

class CurrencyCached @Inject constructor(private val currencyAPI: CurrencyAPI, private val bestCurrency: BestCurrency){
    private var cache: Single<List<Currency>>? = null

    fun getCurrencyData(forceNetworkUpdate: Boolean = false) : Single<List<Currency>> {
        if (forceNetworkUpdate || cache==null) {
            //TODO change limit
            cache = currencyAPI.loadCurrencies(1, 5000, bestCurrency.getCurrency(), BuildConfig.API_TOKEN)
                .subscribeOn(Schedulers.io())
                .flatMap { value -> convert(value) }
                .cache()
        }

        return cache!!
    }
}
