package net.kangwonlee.seoulprice.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import net.kangwonlee.seoulprice.R
import net.kangwonlee.seoulprice.databinding.FragmentMainBinding
import net.kangwonlee.seoulprice.viewmodel.MainViewModel
import net.kangwonlee.seoulprice.viewmodel.SplashViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class MainFragment : DaggerFragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.apply {
            viewModel = this@MainFragment.viewModel
        }

        val pref = requireContext().getSharedPreferences("init-data",MODE_PRIVATE)
        if(pref.contains("registerDate") && pref.contains("serialNumber")) {
            //viewModel.getDataList()
        }
        else {
            val nowDate: LocalDate = LocalDate.now()
            viewModel.getDataList(nowDate)
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner, {
            if(it == 100L) {
                binding.clMainProgress.visibility = View.GONE
            }
            else {
                binding.tvProgress.text = "서울물가 데이터 수신중...(${it}%)"
            }
        })

        viewModel.dataListLiveData.observe(viewLifecycleOwner, {
            Timber.d("데이터 입력!")
        })


    }
}