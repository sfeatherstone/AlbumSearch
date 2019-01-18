package uk.co.wedgetech.blockchain.dagger

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.wedgetech.blockchain.BuildConfig
import uk.co.wedgetech.blockchain.network.CurrencyAPI
import uk.co.wedgetech.blockchain.repository.CurrencyCached
import javax.inject.Singleton

@Module
object RepositoryModule {

    @JvmStatic @Provides
    @Singleton
    fun provideCurrencyCached(): CurrencyCached = CurrencyCached()
}