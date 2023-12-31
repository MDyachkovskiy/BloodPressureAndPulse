package gb.com.bloodpressureandpulse.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import gb.com.bloodpressureandpulse.R
import gb.com.bloodpressureandpulse.databinding.ActivityMainBinding
import gb.com.bloodpressureandpulse.view.health_parameter_fragment.HealthMetricsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, HealthMetricsFragment.newInstance())
            .commit()
    }
}