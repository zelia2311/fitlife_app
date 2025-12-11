package com.example.fitlifeapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlanAdapter(
    private val data: List<Array<String>>,
    private val listener: OnStartClick
) : RecyclerView.Adapter<PlanAdapter.VH>() {

    interface OnStartClick {
        fun onStart(view: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = data[position]
        holder.title.text = item[0]
        holder.subtitle.text = item[1]

        holder.itemView.setOnClickListener {
            it.tag = item
            listener.onStart(it)
        }
    }

    override fun getItemCount(): Int = data.size

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(android.R.id.text1)
        val subtitle: TextView = view.findViewById(android.R.id.text2)
    }
}
