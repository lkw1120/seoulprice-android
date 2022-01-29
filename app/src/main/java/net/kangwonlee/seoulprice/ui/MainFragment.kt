package net.kangwonlee.seoulprice.ui

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import net.kangwonlee.seoulprice.R
import net.kangwonlee.seoulprice.databinding.FragmentMainBinding
import net.kangwonlee.seoulprice.viewmodel.MainViewModel
import timber.log.Timber
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class MainFragment : DaggerFragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    private val pref by lazy {
        requireContext().getSharedPreferences("init-data", MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.apply {
            viewModel = this@MainFragment.viewModel
        }

        val nowDate: LocalDate = LocalDate.now()
        val registerDate = pref.getString("registerDate",null)
        if (registerDate.isNullOrEmpty() || registerDate != nowDate.toString()) {
            viewModel.getDataList(nowDate)
        }
        else {
            viewModel.loadingFinish()
        }

        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            if (it == 100L) {
                binding.clMainProgress.visibility = View.GONE
                pref.edit()
                    .putString("registerDate", LocalDate.now().toString())
                    .apply()
            } else {
                binding.tvProgress.text =
                    String.format(resources.getString(R.string.loading_data),it)
            }
        }

        viewModel.dataListLiveData.observe(viewLifecycleOwner) {
            Timber.d("데이터 입력!")
        }
    }
}