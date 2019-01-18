package uk.co.wedgetech.blockchain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import uk.co.wedgetech.blockchain.model.Currency
import uk.co.wedgetech.blockchain.repository.CurrencyCached
import javax.inject.Inject


class CurrencyListViewModel @Inject constructor(private val currencyCached: CurrencyCached) : ViewModel() {

    private val currenciesMutable : MutableLiveData<List<Currency>> = MutableLiveData()
    val currencies: LiveData<List<Currency>> = currenciesMutable

    private val disposables = CompositeDisposable()

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


    override fun onCleared() {
        super.onCleared()
        disposables.clear()

    }
}
