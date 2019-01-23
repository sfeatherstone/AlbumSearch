package uk.co.wedgetech.musicsearch

import android.app.Activity
import android.app.Application
import android.content.Context
import uk.co.wedgetech.musicsearch.dagger.ApplicationComponent
import uk.co.wedgetech.musicsearch.dagger.DaggerApplicationComponent
import uk.co.wedgetech.musicsearch.dagger.DaggerComponentProvider

class MusicSearchApplication: Application(), DaggerComponentProvider {
    override val coreComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .applicationContext(applicationContext)
            .build()
    }

    companion object {
        @JvmStatic
        fun appComponent(context: Context) =
            (context.applicationContext as MusicSearchApplication).coreComponent
    }
}

fun Activity.appComponent() = MusicSearchApplication.appComponent(this)
