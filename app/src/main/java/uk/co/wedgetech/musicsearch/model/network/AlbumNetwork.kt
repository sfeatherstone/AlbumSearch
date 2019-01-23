package uk.co.wedgetech.musicsearch.model.network

import com.google.gson.annotations.SerializedName

data class AlbumNetwork(val name: String, val artist:String, @SerializedName("image") val images:List<AlbumImage>,
                        val mbid:String, val tracks: AlbumTracks?)