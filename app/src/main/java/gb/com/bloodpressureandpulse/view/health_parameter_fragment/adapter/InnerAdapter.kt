package gb.com.bloodpressureandpulse.view.health_parameter_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gb.com.bloodpressureandpulse.databinding.ItemBloodPressurePulseBinding
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import java.text.SimpleDateFormat
import java.util.Locale

class InnerAdapter : RecyclerView.Adapter<InnerAdapter.ViewHolder>() {

    private val data = mutableListOf<VitalSigns>()

    fun setData(newData: List<VitalSigns>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemBloodPressurePulseBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VitalSigns) {
            with(binding) {
                tvTime.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(data.date)
                bloodPressureFirstValue.text = data.systolicPressure.toString()
                bloodPressureSecondValue.text = data.diastolicPressure.toString()
                pulse.text = data.pulse.toString()
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