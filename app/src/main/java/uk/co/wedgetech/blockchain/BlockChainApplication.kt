package uk.co.wedgetech.blockchain

import android.app.Activity
import android.app.Application
import android.content.Context
import uk.co.wedgetech.blockchain.dagger.ApplicationComponent
import uk.co.wedgetech.blockchain.dagger.DaggerApplicationComponent
import uk.co.wedgetech.blockchain.dagger.DaggerComponentProvider

class BlockChainApplication: Application(), DaggerComponentProvider {
    override val coreComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationContext(applicationContext)
            .build()
    }

    companion object {
        @JvmStatic
        fun appComponent(context: Context) =
            (context.applicationContext as BlockChainApplication).coreComponent
    }
}

fun Activity.appComponent() = BlockChainApplication.appComponent(this)
