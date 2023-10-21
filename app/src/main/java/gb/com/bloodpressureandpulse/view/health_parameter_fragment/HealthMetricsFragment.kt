package gb.com.bloodpressureandpulse.view.health_parameter_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gb.com.bloodpressureandpulse.databinding.FragmentHealthParameterBinding
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import org.koin.androidx.viewmodel.ext.android.viewModel



class HealthMetricsFragment : Fragment() {

    private var _binding: FragmentHealthParameterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HealthMetricsViewModel by viewModel()

    companion object {
        fun newInstance() = HealthMetricsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHealthParameterBinding.inflate(inflater, container, false)
        initObservers()
        handleFabButton()
        return binding.root
    }

    private fun initObservers() {
        viewModel.healthMetricsLiveData.observe(viewLifecycleOwner) {vitalSigns ->
            initRecyclerView(vitalSigns)
        }
    }

    private fun initRecyclerView(vitalSigns: VitalSigns) {

    }

    private fun handleFabButton() {
        binding.fabGetVitalSigns.setOnClickListener {
            viewModel.getVitalSigns()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}