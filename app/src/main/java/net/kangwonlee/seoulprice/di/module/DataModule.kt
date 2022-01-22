package net.kangwonlee.seoulprice.di.module

import dagger.Module
import dagger.Provides
import net.kangwonlee.seoulprice.datasource.remote.ApiService
import net.kangwonlee.seoulprice.repository.ApiRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideApiRepository(@Named("ApiService") apiService: ApiService): ApiRepository =
        ApiRepository(apiService)
}