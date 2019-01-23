package uk.co.wedgetech.musicsearch.model.network

import com.google.gson.annotations.SerializedName

data class AlbumTracks(@SerializedName("track") val tracks:List<AlbumTrack>)