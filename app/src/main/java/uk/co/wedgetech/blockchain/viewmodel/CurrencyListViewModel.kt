package uk.co.wedgetech.blockchain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import uk.co.wedgetech.blockchain.BuildConfig
import uk.co.wedgetech.blockchain.model.CurrencyListingData
import uk.co.wedgetech.blockchain.model.CurrencyListingPayload
import uk.co.wedgetech.blockchain.network.CurrencyAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver


class CurrencyListViewModel : ViewModel() {

    private val currenciesMutable : MutableLiveData<List<CurrencyListingData>> = MutableLiveData()
    val currencies: LiveData<List<CurrencyListingData>> = currenciesMutable

    private val disposables = CompositeDisposable()

    fun fetchCurrencies() {
            val subscribe =
            CurrencyAPI.currencyAPI.loadHosts(1,50,"GBP", BuildConfig.API_TOKEN)
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

    override fun onCleared() {
        super.onCleared()
        disposables.clear()

    }
}
