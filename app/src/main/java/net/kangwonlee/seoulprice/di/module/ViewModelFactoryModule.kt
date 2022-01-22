package net.kangwonlee.seoulprice.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import net.kangwonlee.seoulprice.viewmodel.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}
