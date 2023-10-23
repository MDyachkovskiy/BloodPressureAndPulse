package gb.com.bloodpressureandpulse.di

import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import gb.com.bloodpressureandpulse.model.datasource.VitalSignsGenerator
import gb.com.bloodpressureandpulse.model.repository.HealthMetricsRepository
import gb.com.bloodpressureandpulse.model.repository.HealthMetricsRepositoryImpl
import gb.com.bloodpressureandpulse.view.health_parameter_fragment.HealthMetricsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { VitalSignsGenerator() }
    single <HealthMetricsRepository> { HealthMetricsRepositoryImpl(get(), get()) }
    viewModel { HealthMetricsViewModel(get()) }
}

val firebaseModule = module {
    single { FirebaseApp.initializeApp(androidContext()) }
    single { FirebaseFirestore.getInstance() }
}