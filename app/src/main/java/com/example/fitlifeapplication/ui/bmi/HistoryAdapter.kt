package com.example.fitlifeapplication.ui.bmi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifeapplication.databinding.ItemHistoryBinding

data class WeightHistory(val date: String, val weight: Double)

class HistoryAdapter(private val historyList: List<WeightHistory>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount() = historyList.size

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: WeightHistory) {
            binding.tvHistoryDate.text = history.date
            binding.tvHistoryWeight.text = "${history.weight} kg"
        }
    }
}
