package uk.co.wedgetech.blockchain.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import uk.co.wedgetech.blockchain.model.Currency

class DetailsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private lateinit var currencies : List<Currency>

    fun setItems(items :List<Currency>) {
        this.currencies = items
    }
    override fun getCount(): Int  = currencies.size

    override fun getItem(i: Int): Fragment {
        return CurrencyDetailFragment.newInstance(i)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "OBJECT " + (position + 1)
    }
}