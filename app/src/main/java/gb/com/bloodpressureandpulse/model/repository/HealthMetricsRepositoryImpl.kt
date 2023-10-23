package gb.com.bloodpressureandpulse.model.repository

import com.google.firebase.firestore.FirebaseFirestore
import gb.com.bloodpressureandpulse.model.datasource.VitalSignsGenerator
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HealthMetricsRepositoryImpl(
    firestore: FirebaseFirestore,
    private val generator: VitalSignsGenerator
) : HealthMetricsRepository {

    private val collection = firestore.collection("vitalSigns")
    override val vitalSignsList: MutableList<VitalSigns> = mutableListOf()
    override fun getVitalSigns(): VitalSigns {
        val newVitalSigns = generator.generateVitalSigns()
        collection.add(newVitalSigns)
        vitalSignsList.add(newVitalSigns)
        return newVitalSigns
    }

    override suspend fun getAllVitalSigns(): List<VitalSigns> {
        return withContext(Dispatchers.IO) {
            val dataShapshot = collection.get().await()
            val list = dataShapshot.documents.mapNotNull {
                it.toObject(VitalSigns::class.java)
            }
            vitalSignsList.clear()
            vitalSignsList.addAll(list)
            list
        }
    }
}
