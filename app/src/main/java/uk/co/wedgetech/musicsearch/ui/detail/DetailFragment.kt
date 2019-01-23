package uk.co.wedgetech.musicsearch.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_album_detail.*

import uk.co.wedgetech.musicsearch.R
import uk.co.wedgetech.musicsearch.dagger.injector
import uk.co.wedgetech.musicsearch.model.Album
import uk.co.wedgetech.musicsearch.viewmodel.AlbumDetailViewModel


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
            it?.let { albums ->
                    //TODO format album correctly
                    name.text = albums.name
/*
                    rank.text = currencies[currencyPosition].rank.toString()
                    price.text = currencies[currencyPosition].price
                    market_cap.text = currencies[currencyPosition].marketCap
                    volume.text = currencies[currencyPosition].volume24h
                    circulating_supply.text = currencies[currencyPosition].circulatingSupply
                    max_supply.text = currencies[currencyPosition].maxSupply
*/
            }

        }

        viewModel.album.observe(this, albumObserver)
        viewModel.getAlbumDetails(albumId)
    }
    companion object {
        private const val ARG_ALBUM_ID = "ALBUM_ID"

        @JvmStatic
        fun newInstance(albumId: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ALBUM_ID, albumId)
                }
            }
    }
}
