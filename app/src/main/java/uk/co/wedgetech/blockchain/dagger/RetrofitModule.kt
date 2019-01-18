package uk.co.wedgetech.blockchain.dagger

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.wedgetech.blockchain.BuildConfig
import uk.co.wedgetech.blockchain.network.CurrencyAPI
import javax.inject.Singleton

@Module
object RetrofitModule {

    @JvmStatic @Provides
    @Singleton
    fun provideCurrencyApi(): CurrencyAPI = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create<CurrencyAPI>(CurrencyAPI::class.java)
}