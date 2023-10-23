package gb.com.bloodpressureandpulse.view.health_parameter_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import gb.com.bloodpressureandpulse.databinding.FragmentHealthParameterBinding
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import gb.com.bloodpressureandpulse.view.health_parameter_fragment.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HealthMetricsFragment : Fragment() {

    private var _binding: FragmentHealthParameterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HealthMetricsViewModel by viewModel()
    private val mainAdapter = MainAdapter()

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
        viewModel.fetchAllVitalSigns()
        return binding.root
    }

    private fun initObservers() {
        viewModel.healthMetricsLiveData.observe(viewLifecycleOwner) {vitalSigns ->
            initRecyclerView(vitalSigns)
        }
    }

    private fun initRecyclerView(vitalSigns: List<VitalSigns>) {
        with(binding) {
            mainAdapter.setData(vitalSigns)
            healthParameterRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            healthParameterRecyclerView.adapter = mainAdapter
        }
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