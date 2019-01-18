package uk.co.wedgetech.blockchain.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import uk.co.wedgetech.blockchain.network.CurrencyAPI
import uk.co.wedgetech.blockchain.repository.CurrencyCached
import uk.co.wedgetech.blockchain.util.BestCurrency
import javax.inject.Singleton

@Module( includes = [ RetrofitModule::class ])
object RepositoryModule {
    @JvmStatic @Provides
    @Singleton
    fun provideCurrencyCached(currencyAPI: CurrencyAPI, bestCurrency: BestCurrency): CurrencyCached = CurrencyCached(currencyAPI, bestCurrency)

    @JvmStatic @Provides
    fun provideBestCurrency(context: Context): BestCurrency = BestCurrency(context)
}