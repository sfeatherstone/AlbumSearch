package uk.co.wedgetech.blockchain

import android.app.Activity
import android.app.Application
import android.content.Context
import uk.co.wedgetech.blockchain.dagger.CoreComponent
import uk.co.wedgetech.blockchain.dagger.DaggerCoreComponent

class BlockChainApplication: Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent
            .builder()
            .build()
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as BlockChainApplication).coreComponent
    }
}

fun Activity.coreComponent() = BlockChainApplication.coreComponent(this)
