package uk.co.wedgetech.blockchain.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uk.co.wedgetech.blockchain.viewmodel.CurrencyListViewModel
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
    fun currencyListViewModelFactory(): ViewModelFactory<CurrencyListViewModel>
}
