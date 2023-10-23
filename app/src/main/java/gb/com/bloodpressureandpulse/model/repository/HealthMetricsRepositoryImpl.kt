package gb.com.bloodpressureandpulse.model.repository

import com.google.firebase.database.FirebaseDatabase
import gb.com.bloodpressureandpulse.model.datasource.VitalSignsGenerator
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HealthMetricsRepositoryImpl(
    firebaseDatabase: FirebaseDatabase,
    private val generator: VitalSignsGenerator
) : HealthMetricsRepository {

    private val ref = firebaseDatabase.getReference("vitalSigns")
    override val vitalSignsList: MutableList<VitalSigns> = mutableListOf()
    override fun getVitalSigns(): VitalSigns {
        val newVitalSigns = generator.generateVitalSigns()
        ref.push().setValue(newVitalSigns)
        vitalSignsList.add(newVitalSigns)
        return newVitalSigns
    }

    override suspend fun getAllVitalSigns(): List<VitalSigns> {
        return withContext(Dispatchers.IO) {
            val dataShapshot = ref.get().await()
            val list = dataShapshot.children.mapNotNull {
                it.getValue(VitalSigns::class.java)
            }
            vitalSignsList.clear()
            vitalSignsList.addAll(list)
            list
        }
    }
}
