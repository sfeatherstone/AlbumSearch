package uk.co.wedgetech.musicsearch.dagger

import dagger.Module
import dagger.Provides
import uk.co.wedgetech.musicsearch.network.LastFmApi
import uk.co.wedgetech.musicsearch.repository.AlbumRepository
import javax.inject.Singleton

@Module( includes = [ RetrofitModule::class ])
object RepositoryModule {
    @JvmStatic @Provides
    @Singleton
    fun provideCurrencyCached(lastFmAPI: LastFmApi): AlbumRepository = AlbumRepository(lastFmAPI)

}