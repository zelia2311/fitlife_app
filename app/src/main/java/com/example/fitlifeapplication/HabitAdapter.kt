package com.example.fitlifeapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class HabitAdapter(
    private val context: Context,
    private val habits: List<Habit>,
    private val onHabitCheck: () -> Unit
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    private val prefs = context.getSharedPreferences("HabitPrefs", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_habit, parent, false)  // layout itemmu
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]

        holder.checkBox.text = habit.name

        val saved = prefs.getBoolean(habit.name, false)
        holder.checkBox.isChecked = saved
        habit.completed = saved

        holder.checkBox.setOnCheckedChangeListener { _, checked ->
            habit.completed = checked
            prefs.edit().putBoolean(habit.name, checked).apply()
            onHabitCheck()
        }
    }

    override fun getItemCount() = habits.size

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkboxHabit)
    }
}
