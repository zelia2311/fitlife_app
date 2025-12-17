package com.example.fitlifeapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifeapplication.databinding.ItemWorkoutPlanBinding

class PlanAdapter(
    private val data: List<Exercise>,
    private val listener: OnStartClick
) : RecyclerView.Adapter<PlanAdapter.VH>() {

    interface OnStartClick {
        fun onStart(exercise: Exercise)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemWorkoutPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = data[position]
        holder.bind(item, listener)
    }

    override fun getItemCount(): Int = data.size

    class VH(private val binding: ItemWorkoutPlanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: Exercise, listener: OnStartClick) {
            binding.tvExerciseName.text = exercise.name
            binding.tvExerciseDetails.text = "${exercise.sets} Sets / ${exercise.reps} Reps"

            // Mengatur gambar (jika Anda memiliki logika untuk memuatnya, misal dengan Glide/Picasso)
            // Contoh sederhana untuk placeholder:
            val imageResId = itemView.context.resources.getIdentifier(exercise.imageUrl, "drawable", itemView.context.packageName)
            if (imageResId != 0) {
                binding.ivExerciseImage.setImageResource(imageResId)
            }

            binding.btnStartExercise.setOnClickListener {
                listener.onStart(exercise)
            }
        }
    }
}
