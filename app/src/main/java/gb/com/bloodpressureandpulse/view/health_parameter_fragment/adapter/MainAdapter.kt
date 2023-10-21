package gb.com.bloodpressureandpulse.view.health_parameter_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gb.com.bloodpressureandpulse.databinding.ItemDayBinding
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = mutableListOf<VitalSigns>()
    private val groupedData: Map<Date, List<VitalSigns>>
        get() = data.groupBy { it.date }

    fun setData(newData: List<VitalSigns>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val innerAdapter = InnerAdapter()

        init {
            binding.innerRecyclerView.layoutManager =
                LinearLayoutManager(binding.innerRecyclerView.context)
            binding.innerRecyclerView.adapter = innerAdapter
        }

        fun bind(date: Date, items: List<VitalSigns>) {
            binding.tvDate.text = SimpleDateFormat("dd MMMM", Locale.getDefault())
                .format(date)
            innerAdapter.setData(items)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = groupedData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = groupedData.keys.toList()[position]
        groupedData[date]?.let { holder.bind(date, it) }
    }
}