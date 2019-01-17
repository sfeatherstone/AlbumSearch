package uk.co.wedgetech.blockchain.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.ui.CurrencyList.CurrencyListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CurrencyListFragment.newInstance(), "MAIN")
                .commitNow()
        }

    }
}
