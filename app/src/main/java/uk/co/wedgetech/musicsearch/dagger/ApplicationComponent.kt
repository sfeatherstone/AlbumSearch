package uk.co.wedgetech.musicsearch.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uk.co.wedgetech.musicsearch.viewmodel.AlbumDetailViewModel
import uk.co.wedgetech.musicsearch.viewmodel.AlbumSearchViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder interface Builder {
        @BindsInstance fun applicationContext(applicationContext: Context): Builder
        fun build(): ApplicationComponent
    }
    fun albumSearchViewModelFactory(): ViewModelFactory<AlbumSearchViewModel>
    fun albumDetailViewModelFactory(): ViewModelFactory<AlbumDetailViewModel>
}
