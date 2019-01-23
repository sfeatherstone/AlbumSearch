package uk.co.wedgetech.musicsearch.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import uk.co.wedgetech.musicsearch.BuildConfig
import uk.co.wedgetech.musicsearch.model.Album
import uk.co.wedgetech.musicsearch.model.Track
import uk.co.wedgetech.musicsearch.model.network.AlbumInfoPayload
import uk.co.wedgetech.musicsearch.model.network.AlbumSearchPayload
import uk.co.wedgetech.musicsearch.network.LastFmApi
import javax.inject.Inject

//Convert from network format to UI format model class
private fun convertFromNetwork(value: AlbumSearchPayload):Single<List<Album>> {
    value.results?.let {
        val newList = it.albummatches.albumNetworks.map { album ->
            Album(
                album.mbid,
                album.name,
                album.artist,
                album.images,
                null)}
        return Single.just(newList)
    }
}


private fun convertFromNetwork(value: AlbumInfoPayload):Single<Album> {
    //val tempCurrencyList = mutableListOf<AlbumNetwork>()
    value.album.let { innerAlbum ->
        innerAlbum?.let {
            val tracks = it.tracks?.tracks?.map { track -> Track(track.name, track.duration) }
            return Single.just(Album(it.mbid, it.name, it.artist, it.images,
                tracks))
        }
    }
    throw Exception("Album Not found")
}

class AlbumRepository @Inject constructor(private val lastFmApi: LastFmApi){
    private var cache: Single<List<Album>>? = null

    fun searchAlbums(search: String) : Single<List<Album>> {
        return lastFmApi.searchAlbums(1,search, BuildConfig.API_TOKEN)
                .subscribeOn(Schedulers.io())
                .flatMap { value -> convertFromNetwork(value) }
    }

    fun getAlbumDetails(mbid: String): Single<Album> {
        return lastFmApi.albumInfo(mbid, BuildConfig.API_TOKEN)
            .subscribeOn(Schedulers.io())
            .flatMap { value -> convertFromNetwork(value) }
    }
}
