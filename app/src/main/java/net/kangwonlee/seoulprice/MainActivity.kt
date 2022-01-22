package net.kangwonlee.seoulprice

import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import dagger.android.support.DaggerAppCompatActivity
import net.kangwonlee.seoulprice.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
    }
}