package gb.com.bloodpressureandpulse.view.health_parameter_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import gb.com.bloodpressureandpulse.R
import gb.com.bloodpressureandpulse.databinding.ItemBloodPressurePulseBinding
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import java.text.SimpleDateFormat
import java.util.Locale

class InnerAdapter : RecyclerView.Adapter<InnerAdapter.ViewHolder>() {

    private val data = mutableListOf<VitalSigns>()
    fun setData(newData: List<VitalSigns>) {
        data.clear()
        val sortedData = newData.sortedBy { it.date }
        data.addAll(sortedData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemBloodPressurePulseBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VitalSigns) {
            with(binding) {
                val systolic = data.systolicPressure
                val diastolic = data.diastolicPressure

                tvTime.text = data.date?.let {
                    SimpleDateFormat("HH:mm", Locale.getDefault()).format(it)
                }
                bloodPressureFirstValue.text = systolic.toString()
                bloodPressureSecondValue.text = diastolic.toString()
                pulse.text = data.pulse.toString()

                when {
                    systolic in 110..129 -> {
                        root.background =
                            ContextCompat.getDrawable(root.context, R.drawable.good_vital_background)
                    }
                    (systolic in 130..139 ) || (systolic in 100..110) -> {
                        root.background =
                            ContextCompat.getDrawable(root.context, R.drawable.wary_vital_background)
                    }
                    (systolic >= 140 ) || (systolic <=100 ) -> {
                        root.background =
                            ContextCompat.getDrawable(root.context, R.drawable.alert_vital_background)
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBloodPressurePulseBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vitalSigns = data[position]
        holder.bind(vitalSigns)
    }
}