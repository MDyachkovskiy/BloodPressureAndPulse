package gb.com.bloodpressureandpulse.view.health_parameter_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gb.com.bloodpressureandpulse.databinding.ItemDayBinding
import gb.com.bloodpressureandpulse.model.domain.VitalSigns
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = mutableListOf<VitalSigns>()
    private val dates = mutableListOf<Date>()
    private var groupedData = mutableMapOf<Date?, List<VitalSigns>>()


    fun setData(newData: List<VitalSigns>) {
        data.clear()
        data.addAll(newData)

        groupedData.clear()
        groupedData.putAll(data.groupBy {
            truncateDate(it.date) })

        val newDates = groupedData.keys.filterNotNull().toList()
        val diffCallback = MainDiffUtilCallback(dates, newDates)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        dates.clear()
        dates.addAll(newDates)

        diffResult.dispatchUpdatesTo(this)
    }

    private fun truncateDate(date: Date?): Date? {
        date?.let {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateStr = format.format(it)
            return format.parse(dateStr)
        }
        return null
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

    private class MainDiffUtilCallback(
        private val oldList: List<Date>,
        private val newList: List<Date>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = dates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = dates[position]
        val items = groupedData[date] ?: emptyList()
        holder.bind(date, items)
    }
}