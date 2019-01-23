package uk.co.wedgetech.musicsearch.model.network

import com.google.gson.annotations.SerializedName

data class AlbumMatches(@SerializedName("album")val albumNetworks:List<AlbumNetwork>)