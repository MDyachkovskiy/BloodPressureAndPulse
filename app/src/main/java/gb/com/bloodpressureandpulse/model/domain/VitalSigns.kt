package gb.com.bloodpressureandpulse.model.domain

import java.util.Date

data class VitalSigns(
    var systolicPressure: Int = 0,
    var diastolicPressure: Int = 0,
    var pulse: Int = 0,
    var date: Date? = null
)