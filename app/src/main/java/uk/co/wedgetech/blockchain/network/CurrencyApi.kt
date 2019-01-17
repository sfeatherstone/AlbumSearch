package uk.co.wedgetech.blockchain.network

import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import uk.co.wedgetech.blockchain.BuildConfig
import uk.co.wedgetech.blockchain.model.network.CurrencyListingPayload

interface CurrencyAPI {
    @GET("/v1/cryptocurrency/listings/latest")
    fun loadCurrencies(@Query("start") start:Int,
                  @Query("limit") limit:Int,
                  @Query("convert") currency:String,
                  @Header("X-CMC_PRO_API_KEY") apiKey: String)
            : Single<CurrencyListingPayload>

    companion object {
        //Added to allow for testing
        internal var BASE_URL : String = BuildConfig.BASE_API

        val currencyAPI : CurrencyAPI = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create<CurrencyAPI>(CurrencyAPI::class.java)

        //val currencyAPI : CurrencyAPI = retrofit.create<CurrencyAPI>(CurrencyAPI::class.java)
    }

}