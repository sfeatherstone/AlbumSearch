package uk.co.wedgetech.musicsearch.model.network

import com.google.gson.annotations.SerializedName

data class AlbumImage(val size:String, @SerializedName("#text") val url:String)

data class AlbumTrack(val name:String, val duration:Int)

data class AlbumTracks(@SerializedName("track") val tracks:List<AlbumTrack>)

data class Album(val name: String, val artist:String,@SerializedName("image") val images:List<AlbumImage>,
                 val mbid:String, val tracks:AlbumTracks?)

data class AlbumInfoPayload(val error:Int?, val message:String?, val album:Album?)


data class AlbumMatches(@SerializedName("album")val albums:List<Album>)

data class AlbumSearchResult(@SerializedName("opensearch:totalResults") val totalCount:Int,
                             val albummatches: AlbumMatches)

data class AlbumSearchPayload(val error:Int?, val message:String?, val results: AlbumSearchResult?)
