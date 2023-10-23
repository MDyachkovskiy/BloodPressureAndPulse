package gb.com.bloodpressureandpulse.view.health_parameter_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import gb.com.bloodpressureandpulse.model.repository.HealthMetricsRepository
import kotlinx.coroutines.launch

class HealthMetricsViewModel(
    private val repository: HealthMetricsRepository
) : ViewModel() {

    private val _healthMetricsLiveData: MutableLiveData<List<VitalSigns>> = MutableLiveData()
    val healthMetricsLiveData: LiveData<List<VitalSigns>> get() = _healthMetricsLiveData

    fun getVitalSigns() {
        viewModelScope.launch {
            repository.getVitalSigns()
            _healthMetricsLiveData.postValue(repository.vitalSignsList)
        }
    }

    fun fetchAllVitalSigns() {
        viewModelScope.launch {
            val allVitalSigns= repository.getAllVitalSigns()
            _healthMetricsLiveData.postValue(allVitalSigns)
        }
    }
}