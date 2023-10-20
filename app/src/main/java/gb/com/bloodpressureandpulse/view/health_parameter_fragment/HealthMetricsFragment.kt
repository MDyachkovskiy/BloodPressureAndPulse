package gb.com.bloodpressureandpulse.view.health_parameter_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gb.com.bloodpressureandpulse.databinding.FragmentHealthParameterBinding


class HealthMetricsFragment : Fragment() {

    private var _binding: FragmentHealthParameterBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = HealthMetricsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHealthParameterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}