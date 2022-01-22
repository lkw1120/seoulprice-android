package net.kangwonlee.seoulprice.repository

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.kangwonlee.seoulprice.BuildConfig
import net.kangwonlee.seoulprice.datasource.entity.ListNecessariesPricesService
import net.kangwonlee.seoulprice.datasource.remote.ApiService
import timber.log.Timber
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getDataListRanged(start: Int, end: Int): Single<ListNecessariesPricesService> {
        return apiService.getDataListRanged(BuildConfig.API_KEY, start,end)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { it.listNecessariesPricesService }
    }
}