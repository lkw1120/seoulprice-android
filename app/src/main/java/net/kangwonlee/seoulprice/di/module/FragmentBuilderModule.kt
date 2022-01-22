package net.kangwonlee.seoulprice.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.kangwonlee.seoulprice.ui.MainFragment
import net.kangwonlee.seoulprice.ui.SplashFragment

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment
}
