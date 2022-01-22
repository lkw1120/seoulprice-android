package net.kangwonlee.seoulprice.datasource.remote

import android.content.Context
import net.kangwonlee.seoulprice.R
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnection(context: Context) {
    private val baseUrl: String = context.getString(R.string.api_url)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): ApiService =
        retrofit.create(ApiService::class.java)

}