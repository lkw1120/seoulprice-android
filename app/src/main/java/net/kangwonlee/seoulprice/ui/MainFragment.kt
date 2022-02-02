package net.kangwonlee.seoulprice.ui

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dagger.android.support.DaggerFragment
import net.kangwonlee.seoulprice.R
import net.kangwonlee.seoulprice.databinding.FragmentMainBinding
import net.kangwonlee.seoulprice.viewmodel.MainViewModel
import timber.log.Timber
import java.time.LocalDate
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
            lifecycleOwner = viewLifecycleOwner

            (activity as AppCompatActivity).apply {
                setSupportActionBar(toolbar)
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowTitleEnabled(false)
                setHasOptionsMenu(true)
            }
        }


        initData()

    }

    private fun initData() {

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home -> {
            OssLicensesMenuActivity.setActivityTitle(getString(R.string.oss_license))
            startActivity(
                Intent(
                    requireContext(),
                    OssLicensesMenuActivity::class.java
                )
            )
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}