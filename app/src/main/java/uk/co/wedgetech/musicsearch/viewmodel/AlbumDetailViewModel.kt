package uk.co.wedgetech.musicsearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import uk.co.wedgetech.musicsearch.model.Album
import uk.co.wedgetech.musicsearch.repository.AlbumRepository
import javax.inject.Inject


class AlbumDetailViewModel @Inject constructor(private val albumRepository: AlbumRepository) : ViewModel() {

    private val albumMutable : MutableLiveData<Album> = MutableLiveData()
    val album: LiveData<Album> = albumMutable

    private val disposables = CompositeDisposable()

    fun getAlbumDetails(mbid:String) {
        val subscribe = albumRepository.getAlbumDetails(mbid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                object : DisposableSingleObserver<Album>() {

                    override fun onSuccess(value: Album) {
                        albumMutable.value = value
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
