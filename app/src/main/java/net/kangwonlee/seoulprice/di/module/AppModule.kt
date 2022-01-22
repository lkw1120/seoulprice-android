package net.kangwonlee.seoulprice.di.module

import dagger.Module

@Module(
    includes = [
        ContextModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface AppModule