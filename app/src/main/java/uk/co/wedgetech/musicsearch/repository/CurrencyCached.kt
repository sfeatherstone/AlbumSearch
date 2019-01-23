package uk.co.wedgetech.musicsearch.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import uk.co.wedgetech.musicsearch.BuildConfig
import uk.co.wedgetech.musicsearch.model.Album
import uk.co.wedgetech.musicsearch.model.network.AlbumInfoPayload
import uk.co.wedgetech.musicsearch.model.network.AlbumSearchPayload
import uk.co.wedgetech.musicsearch.network.LastFmApi
import javax.inject.Inject

//Convert from network format to UI format model class
private fun convertFromNetwork(value: AlbumSearchPayload):Single<List<Album>> {
    val tempCurrencyList = mutableListOf<Album>()
    value.results?.let {
        for (album in it.albummatches.albums) {
            tempCurrencyList.add(
                Album(
                    album.mbid,
                    album.name,
                    album.artist,
                    album.images,
                    null)
            )
        }
        return Single.just(tempCurrencyList)
    }
}


private fun convertFromNetwork(value: AlbumInfoPayload):Single<Album> {
    //val tempCurrencyList = mutableListOf<Album>()
    value.album.let {innerAlbum ->
        innerAlbum?.let {
            return Single.just(Album(it.mbid, it.name, it.artist, it.images, listOf()))
        }
    }
    throw Exception("Album Not found")
}

class CurrencyCached @Inject constructor(private val lastFmApi: LastFmApi){
    private var cache: Single<List<Album>>? = null

    fun searchAlbums() : Single<List<Album>> {
        return lastFmApi.searchAlbums(1,"believe", BuildConfig.API_TOKEN)
                .subscribeOn(Schedulers.io())
                .flatMap { value -> convertFromNetwork(value) }
    }

    fun getAlbumDetails(mbid: String): Single<Album> {
        return lastFmApi.albumInfo(mbid, BuildConfig.API_TOKEN)
            .subscribeOn(Schedulers.io())
            .flatMap { value -> convertFromNetwork(value) }
    }
}
