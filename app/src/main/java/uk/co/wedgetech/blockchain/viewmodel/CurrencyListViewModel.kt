package uk.co.wedgetech.blockchain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uk.co.wedgetech.blockchain.BuildConfig
import uk.co.wedgetech.blockchain.model.network.CurrencyListingData
import uk.co.wedgetech.blockchain.model.network.CurrencyListingPayload
import uk.co.wedgetech.blockchain.network.CurrencyAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.repository.CurrencyCached


class CurrencyListViewModel : ViewModel() {

    private val currencyCached = CurrencyCached()

    private val currenciesMutable : MutableLiveData<List<Currency>> = MutableLiveData()
    val currencies: LiveData<List<Currency>> = currenciesMutable

    val size :Int = currencies.value?.size ?: 0

    private val disposables = CompositeDisposable()

/*
    fun fetchCurrencies() {
        val subscribe = CurrencyAPI.currencyAPI.loadHosts(1,50,"GBP", BuildConfig.API_TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                object : DisposableSingleObserver<CurrencyListingPayload>() {

                    override fun onSuccess(value: CurrencyListingPayload) {
                        currenciesMutable.value = value.data
                    }

                    override fun onError(e: Throwable) {
                    }

                }
            )
        disposables.add(subscribe)
    }
*/

    fun fetchCurrencies() {
        val subscribe = currencyCached.getCurrencyData(false)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                object : DisposableSingleObserver<List<Currency>>() {

                    override fun onSuccess(value: List<Currency>) {
                        currenciesMutable.value = value
                    }

                    override fun onError(e: Throwable) {
                    }

                }
            )
        disposables.add(subscribe)
    }


    fun getCurrency(position: Int): Currency? {
        return currencies.value?.get(position)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()

    }
}
