package uk.co.wedgetech.blockchain.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import uk.co.wedgetech.blockchain.R
import uk.co.wedgetech.blockchain.ui.currencylist.CurrencyListFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
/*            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CurrencyListFragment.newInstance(), "MAIN")
                .commitNow()*/
        }
    }

    override fun onSupportNavigateUp()
            = findNavController(R.id.nav_host_fragment).navigateUp()
}
