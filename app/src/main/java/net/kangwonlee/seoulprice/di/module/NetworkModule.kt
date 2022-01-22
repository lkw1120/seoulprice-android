package net.kangwonlee.seoulprice.di.module

import dagger.Module
import dagger.Provides
import net.kangwonlee.seoulprice.constant.ServerInfo
import net.kangwonlee.seoulprice.datasource.remote.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("Retrofit")
    fun provideRetrofit(@Named("OkHttpClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ServerInfo.baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("OkHttpClient")
    fun provideHttpClient(@Named("RestInterceptor") interceptor: Interceptor): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        // timeout 정의
        okHttpClientBuilder.readTimeout(15, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(15, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(15, TimeUnit.SECONDS)

        //authorization interceptor
        okHttpClientBuilder.addInterceptor(interceptor)
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    @Named("RestInterceptor")
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    @Named("ApiService")
    fun provideApiService(@Named("Retrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}