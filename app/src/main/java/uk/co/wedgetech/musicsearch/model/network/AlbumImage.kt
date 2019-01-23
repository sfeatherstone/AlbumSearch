package uk.co.wedgetech.musicsearch.model.network

import com.google.gson.annotations.SerializedName

data class AlbumImage(val size:String, @SerializedName("#text") val url:String)