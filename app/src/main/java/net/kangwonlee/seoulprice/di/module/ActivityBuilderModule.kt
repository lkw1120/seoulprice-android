package net.kangwonlee.seoulprice.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.kangwonlee.seoulprice.MainActivity

@Suppress("unused")
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentBuilderModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}