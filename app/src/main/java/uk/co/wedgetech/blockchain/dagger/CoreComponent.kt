package uk.co.wedgetech.blockchain.dagger

import dagger.Component

@Component(
/*
    modules = [
        CoreDataModule::class,
        DesignerNewsDataModule::class,
        DribbbleDataModule::class,
        MarkdownModule::class,
        ProductHuntModule::class,
        SharedPreferencesModule::class
    ]
*/
)
interface CoreComponent {

    @Component.Builder interface Builder {
        fun build(): CoreComponent
        //fun sharedPreferencesModuleModule(module: SharedPreferencesModule): Builder
    }
}
