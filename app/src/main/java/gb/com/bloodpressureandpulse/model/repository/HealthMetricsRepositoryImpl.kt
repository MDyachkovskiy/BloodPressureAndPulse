package gb.com.bloodpressureandpulse.model.repository

import gb.com.bloodpressureandpulse.model.datasource.VitalSignsGenerator
import gb.com.bloodpressureandpulse.model.domain.VitalSigns

class HealthMetricsRepositoryImpl(
    private var generator: VitalSignsGenerator
) : HealthMetricsRepository {
    override fun getVitalSigns(): VitalSigns {
        return generator.generateVitalSigns()
    }
}