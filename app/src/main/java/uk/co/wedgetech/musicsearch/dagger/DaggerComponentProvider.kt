package uk.co.wedgetech.musicsearch.dagger

import android.app.Activity
import androidx.fragment.app.Fragment

interface DaggerComponentProvider {

    val coreComponent: ApplicationComponent
}

val Activity.injector get() = (application as DaggerComponentProvider).coreComponent
val Fragment.injector get() = (activity?.application as DaggerComponentProvider).coreComponent
