package uk.co.wedgetech.musicsearch.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*

import uk.co.wedgetech.musicsearch.R
import uk.co.wedgetech.musicsearch.dagger.injector
import uk.co.wedgetech.musicsearch.model.Album
import uk.co.wedgetech.musicsearch.viewmodel.AlbumDetailViewModel
import android.widget.ArrayAdapter
import uk.co.wedgetech.musicsearch.model.Track


class DetailFragment : Fragment() {
    private lateinit var albumId: String
    private val viewModel by lazy {
        ViewModelProviders.of(this, injector.albumDetailViewModelFactory()).get(AlbumDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumId = it.getString(ARG_ALBUM_ID)?:""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val albumObserver = Observer<Album> {
            it?.let { album ->
                    //TODO format albumNetwork correctly
                    name.text = album.name
                    artist.text = album.artist

                var imageUrl = album.images.findLast { value -> value.size == "extralarge" }
                if (imageUrl==null) {
                    imageUrl = album.images.findLast { value -> value.size == "large" }
                }

                Picasso.get()
                    .load(imageUrl?.url)
                    .into(image)

                //Track list quick and dirty
                tracks.adapter = ArrayAdapter<Track>(context, android.R.layout.simple_list_item_1, album.tracks)
            }

        }

        viewModel.album.observe(this, albumObserver)
        viewModel.getAlbumDetails(albumId)
    }
    companion object {
        private const val ARG_ALBUM_ID = "ALBUM_ID"
    }
}
