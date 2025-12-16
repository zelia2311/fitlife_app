package com.example.fitlifeapplication.ui.fitness

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifeapplication.R
import com.example.fitlifeapplication.databinding.ItemExerciseGoalBinding

data class ExerciseGoal(val name: String, val details: String, val imageResId: Int)

class ExerciseGoalAdapter(private val goals: List<ExerciseGoal>) : RecyclerView.Adapter<ExerciseGoalAdapter.GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val binding = ItemExerciseGoalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(goals[position])
    }

    override fun getItemCount() = goals.size

    inner class GoalViewHolder(private val binding: ItemExerciseGoalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(goal: ExerciseGoal) {
            binding.tvExerciseName.text = goal.name
            binding.tvExerciseDetails.text = goal.details
            binding.ivExerciseImage.setImageResource(goal.imageResId)
        }
    }
}
