package net.kangwonlee.seoulprice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.kangwonlee.seoulprice.repository.ApiRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    val navigatorLiveData: MutableLiveData<NavigateEvent> =
        MutableLiveData()


    fun goMain() {
        navigatorLiveData.postValue(NavigateEvent.GoMain)
    }


    sealed class NavigateEvent {
        object GoMain: NavigateEvent()
    }
}