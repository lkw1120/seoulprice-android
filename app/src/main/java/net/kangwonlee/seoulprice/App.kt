package net.kangwonlee.seoulprice

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import net.kangwonlee.seoulprice.di.DaggerAppComponent

class App : DaggerApplication() {
    private val applicationInjector = DaggerAppComponent.builder().application(this).build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}