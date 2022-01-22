package net.kangwonlee.seoulprice.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import net.kangwonlee.seoulprice.App
import net.kangwonlee.seoulprice.di.module.ActivityBuilderModule
import net.kangwonlee.seoulprice.di.module.AppModule
import net.kangwonlee.seoulprice.di.module.FragmentBuilderModule
import net.kangwonlee.seoulprice.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class
])
interface AppComponent: AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)

}