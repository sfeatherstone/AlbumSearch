package uk.co.wedgetech.musicsearch.ui.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_result_card.*
import kotlinx.android.extensions.LayoutContainer
import uk.co.wedgetech.musicsearch.model.Album

class AlbumSearchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    internal lateinit var album: Album

    fun bindHost(album_: Album, listener: AlbumSearchAdapter.CardViewPressListener) {
        this.album = album_
        album_name.text = album.name
        artist.text =  album.artist

        val medImage = album.images.findLast { value -> value.size == "medium" }

        if (!medImage?.url.isNullOrBlank()) {
            Picasso.get()
                .load(medImage?.url)
                .resize(36, 36)
                .centerCrop()
                .into(image)
        }

        //Click handler
        containerView.setOnClickListener {
            listener.onClick(album)
        }
    }

}