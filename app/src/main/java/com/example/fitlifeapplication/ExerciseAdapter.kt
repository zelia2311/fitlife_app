package com.example.fitlifeapplication // <-- PACKAGE DIPERBAIKI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifeapplication.R // <-- IMPORT R DIPERBAIKI

class ExerciseAdapter(
    private var exercises: List<Exercise>,
    private val onExerciseClicked: (Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int = exercises.size

    fun updateData(newExercises: List<Exercise>) {
        this.exercises = newExercises
        notifyDataSetChanged()
    }

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exerciseName: TextView = itemView.findViewById(R.id.tvExerciseName)
        private val setsReps: TextView = itemView.findViewById(R.id.tvSetsReps)

        fun bind(exercise: Exercise) {
            exerciseName.text = exercise.name
            val repsText = if (exercise.weight != null) {
                "${exercise.sets} sets • ${exercise.reps} reps • ${exercise.weight} lb"
            } else {
                "${exercise.sets} sets • ${exercise.reps} reps"
            }
            setsReps.text = repsText

            itemView.setOnClickListener {
                onExerciseClicked(exercise)
            }
        }
    }
}
