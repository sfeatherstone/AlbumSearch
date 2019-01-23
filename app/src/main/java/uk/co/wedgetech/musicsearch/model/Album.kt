package uk.co.wedgetech.musicsearch.model

import uk.co.wedgetech.musicsearch.model.network.AlbumImage

data class Album(val mbid: String,
                 val name: String,
                 val artist:String,
                 val images:List<AlbumImage>,
                 val tracks: List<Track>?)