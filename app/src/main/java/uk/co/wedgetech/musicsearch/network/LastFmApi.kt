package uk.co.wedgetech.musicsearch.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.wedgetech.musicsearch.model.network.AlbumInfoPayload
import uk.co.wedgetech.musicsearch.model.network.AlbumSearchPayload

interface LastFmApi {
    @GET("?method=album.search&format=json&limit=50")
    fun searchAlbums(@Query("page") page:Int,
                  @Query("album") album:String,
                  @Query("api_key") apiKey: String)
            : Single<AlbumSearchPayload>

    @GET("?method=album.getinfo&format=json")
    fun albumInfo(@Query("mbid") page:String,
                  @Query("api_key") apiKey: String): Single<AlbumInfoPayload>
}