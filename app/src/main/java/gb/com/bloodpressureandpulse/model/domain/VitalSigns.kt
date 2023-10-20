package gb.com.bloodpressureandpulse.model.domain

import java.util.Date

data class VitalSigns(
    val systolicPressure: Int,
    val diastolicPressure: Int,
    val pulse: Int,
    val date: Date
)