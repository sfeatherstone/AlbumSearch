package uk.co.wedgetech.blockchain.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import uk.co.wedgetech.blockchain.BuildConfig
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.model.network.CurrencyListingPayload
import uk.co.wedgetech.blockchain.network.CurrencyAPI

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


class CurrencyCached {

    private var currencyList:MutableList<Currency> = mutableListOf()
    var cache: Single<List<Currency>>? = null
    //private

    fun getCurrencyData(forceNetworkUpdate: Boolean = false) : Single<List<Currency>> {
        if (forceNetworkUpdate || cache==null) {
            cache = CurrencyAPI.currencyAPI.loadCurrencies(1, 50, "GBP", BuildConfig.API_TOKEN)
                .subscribeOn(Schedulers.io())
                .flatMap { value -> convert(value) }
                .cache()
        }
/*                .subscribe(
                    object : DisposableSingleObserver<Currency>() {

                        override fun onSuccess(value: Currency) {
                            value.data?.let {
                                val tempCurrencyList = mutableListOf<Currency>()
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
                                currencyList = tempCurrencyList
                            }
                        }

                        override fun onError(e: Throwable) {
                        }

                    }
                )
        }*/
        return cache!!
    }
}
