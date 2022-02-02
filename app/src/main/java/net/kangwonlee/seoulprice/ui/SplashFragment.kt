package net.kangwonlee.seoulprice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.kangwonlee.seoulprice.R
import net.kangwonlee.seoulprice.databinding.FragmentSplashBinding
import android.os.Handler
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Job
import net.kangwonlee.seoulprice.viewmodel.SplashViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashFragment : DaggerFragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SplashViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        Single.just(0)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .delay(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                   viewModel.goMain()
            }, {

            }).addTo(CompositeDisposable())

        viewModel.navigatorLiveData.observe(viewLifecycleOwner, ::navigate)
    }

    private fun navigate(event: SplashViewModel.NavigateEvent) {
        when(event) {
            is SplashViewModel.NavigateEvent.GoMain -> {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
        }
    }
}