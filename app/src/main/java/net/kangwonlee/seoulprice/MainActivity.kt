package net.kangwonlee.seoulprice

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity
import net.kangwonlee.seoulprice.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var lastTimeBackPressed: Long = 0
    private val TIME_MILLIS: Long = 2000

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.WHITE
    }

    override fun onBackPressed() {
        navController.currentDestination?.label?.let { label ->
            if(label == "mainFragment") {
                if (System.currentTimeMillis() > lastTimeBackPressed + TIME_MILLIS) {
                    lastTimeBackPressed = System.currentTimeMillis()
                    Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                } else {
                    lastTimeBackPressed = 0 //초기화
                    finish()
                }
            }
            else {
                super.onBackPressed()
            }
        }
    }
}