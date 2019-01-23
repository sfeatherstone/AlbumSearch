package uk.co.wedgetech.musicsearch.model.network

import com.google.gson.annotations.SerializedName

data class AlbumSearchResult(@SerializedName("opensearch:totalResults") val totalCount:Int,
                             val albummatches: AlbumMatches
)