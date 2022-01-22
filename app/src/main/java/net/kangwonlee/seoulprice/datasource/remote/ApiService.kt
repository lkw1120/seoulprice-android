package net.kangwonlee.seoulprice.datasource.remote

import io.reactivex.Single
import net.kangwonlee.seoulprice.datasource.entity.ApiResp
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{apiKey}/json/ListNecessariesPricesService/{start}/{end}")
    fun getDataListRanged(
        @Path("apiKey") apiKey: String,
        @Path("start") start: Int,
        @Path("end") end: Int
    ): Single<ApiResp>
}