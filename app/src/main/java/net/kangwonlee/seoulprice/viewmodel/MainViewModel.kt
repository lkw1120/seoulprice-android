package net.kangwonlee.seoulprice.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import net.kangwonlee.seoulprice.common.SingleLiveEvent
import net.kangwonlee.seoulprice.datasource.entity.Row
import net.kangwonlee.seoulprice.repository.ApiRepository
import timber.log.Timber
import java.math.BigInteger
import java.text.Format
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    val progressLiveData: SingleLiveEvent<Long> =
        SingleLiveEvent()

    val dataListLiveData: SingleLiveEvent<List<Row>> =
        SingleLiveEvent()

    private val compositeDisposable = CompositeDisposable()

    init {
        progressLiveData.postValue(0)
    }

    fun loadingFinish() {
        progressLiveData.postValue(100L)
    }

    fun getDataList(date: LocalDate) {
        Timber.d("오늘 날짜 : $date")
        val limitDate = date
            .minusMonths(3)
            .minusDays(date.dayOfMonth.toLong() - 1)
        Timber.d("오늘 기준 제한 날짜 : $limitDate")
        getDataListRanged(limitDate.toString())
    }

    private fun getDataListRanged(limitDate: String, init: Int = 1) {
        var start = init
        Timber.d("범위 : $start ~ ${start+999}")
        apiRepository.getDataListRanged(start,start+999)
            .subscribe ({
                //Timber.d("날짜 : ${it.row.find { item -> item.pDate >= limitDate }?.pDate}")
                it.row.filter { item -> item.pDate >= limitDate }.let { list ->
                    if (list.isNotEmpty()) {
                        val maxDays = ChronoUnit.DAYS.between(LocalDate.parse(limitDate),LocalDate.now())
                        val days = ChronoUnit.DAYS.between(LocalDate.parse(list.minOf { item -> item.pDate }),LocalDate.now())
                        val nextPercent = days*100/maxDays
                        Timber.d("차이 : $days / $maxDays $nextPercent")
                        progressLiveData.value?.let { nowPercent ->
                            if(nowPercent < nextPercent) {
                                progressLiveData.postValue(nextPercent)
                            }
                        }

                        val subList = mutableListOf<Row>()
                        dataListLiveData.value?.let { data -> subList.addAll(data) }
                        subList.addAll(list)
                        dataListLiveData.postValue(subList)
                        start += 1000
                        getDataListRanged(limitDate, start)
                    }
                    else {
                        progressLiveData.postValue(100)
                        Timber.d("끝!")
                    }
                }
            },{
                throw it
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }



}