package uk.co.wedgetech.musicsearch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uk.co.wedgetech.musicsearch.R
import uk.co.wedgetech.musicsearch.model.Album

class AlbumSearchAdapter(private val listener: CardViewPressListener) : RecyclerView.Adapter<AlbumSearchViewHolder>() {

    interface CardViewPressListener {
        fun onClick(currency: Album)
    }

    private var items: List<Album> = listOf()


    fun setCurrency(currencyList :List<Album>) {
        items = currencyList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_card, parent, false)
        return AlbumSearchViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AlbumSearchViewHolder, position: Int) {
        return holder.bindHost(items[position], listener)
    }

}