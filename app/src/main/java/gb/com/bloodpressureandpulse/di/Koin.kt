package gb.com.bloodpressureandpulse.di

import gb.com.bloodpressureandpulse.model.datasource.VitalSignsGenerator
import gb.com.bloodpressureandpulse.model.repository.HealthMetricsRepository
import gb.com.bloodpressureandpulse.model.repository.HealthMetricsRepositoryImpl
import gb.com.bloodpressureandpulse.view.health_parameter_fragment.HealthMetricsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { VitalSignsGenerator() }
    single <HealthMetricsRepository> { HealthMetricsRepositoryImpl(get()) }
    viewModel { HealthMetricsViewModel(get()) }
}