package uk.co.wedgetech.musicsearch.dagger

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.wedgetech.musicsearch.BuildConfig
import uk.co.wedgetech.musicsearch.network.LastFmApi
import javax.inject.Singleton

@Module
object RetrofitModule {

    //TODO Only apply logging interceptor for debug builds
    @JvmStatic @Provides
    @Singleton
    fun provideCurrencyApi(): LastFmApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API)
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create<LastFmApi>(LastFmApi::class.java)
}
