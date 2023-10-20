package gb.com.bloodpressureandpulse.model.datasource

import android.icu.util.Calendar
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import kotlin.random.Random

class VitalSignsGenerator {

    fun generateVitalSigns() : VitalSigns {
        val systolicPressure = Random.nextInt(90, 141)
        val diastolicPressure = Random.nextInt(60, 91)
        val pulse = Random.nextInt(60, 101)

        val calendar = Calendar.getInstance()
        val date = calendar.time

        return VitalSigns(systolicPressure, diastolicPressure, pulse, date)
    }
}