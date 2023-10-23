package gb.com.bloodpressureandpulse.model.repository

import gb.com.bloodpressureandpulse.model.domain.VitalSigns

interface HealthMetricsRepository {
    fun getVitalSigns(): VitalSigns
    suspend fun getAllVitalSigns(): List<VitalSigns>
    val vitalSignsList: MutableList<VitalSigns>
}