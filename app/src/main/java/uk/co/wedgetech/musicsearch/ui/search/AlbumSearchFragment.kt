package uk.co.wedgetech.musicsearch.ui.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.album_list_fragment.*

import uk.co.wedgetech.musicsearch.dagger.injector
import uk.co.wedgetech.musicsearch.R
import uk.co.wedgetech.musicsearch.model.Album
import uk.co.wedgetech.musicsearch.viewmodel.AlbumSearchViewModel

class AlbumSearchFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, injector.albumSearchViewModelFactory()).get(AlbumSearchViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listener = object : AlbumSearchAdapter.CardViewPressListener {
            override fun onClick(album: Album) {
                view?.findNavController()?.navigate(AlbumSearchFragmentDirections.ActionCurrencyListFragmentToDetailFragment(album.mbid))
            }
        }
        val albumSearchAdaptor = AlbumSearchAdapter(listener)

        //Catch medal data
        val currencyObserver = Observer<List<Album>> { currencies ->
            if (currencies!=null)    {
                albumSearchAdaptor.setCurrency(currencies)
            }
        }

        viewModel.albums.observe(this, currencyObserver)
        viewModel.fetchCurrencies()

        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler.adapter = albumSearchAdaptor
    }

}
