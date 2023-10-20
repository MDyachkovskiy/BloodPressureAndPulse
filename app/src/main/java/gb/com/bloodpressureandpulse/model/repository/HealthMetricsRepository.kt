package gb.com.bloodpressureandpulse.model.repository

import gb.com.bloodpressureandpulse.model.domain.VitalSigns

interface HealthMetricsRepository {
    fun getVitalSigns(): VitalSigns
}