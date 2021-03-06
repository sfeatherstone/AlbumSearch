package uk.co.wedgetech.musicsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import uk.co.wedgetech.musicsearch.model.Album
import uk.co.wedgetech.musicsearch.repository.AlbumRepository
import javax.inject.Inject


class AlbumSearchViewModel @Inject constructor(private val albumRepository: AlbumRepository) : ViewModel() {

    private val albumsMutable : MutableLiveData<List<Album>> = MutableLiveData()
    val albums: LiveData<List<Album>> = albumsMutable

    private val disposables = CompositeDisposable()

    fun searchAlbums(search:String) {
        val subscribe = albumRepository.searchAlbums(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                object : DisposableSingleObserver<List<Album>>() {

                    override fun onSuccess(value: List<Album>) {
                        albumsMutable.value = value
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
