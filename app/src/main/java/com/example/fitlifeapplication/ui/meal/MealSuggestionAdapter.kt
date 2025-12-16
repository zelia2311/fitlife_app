package com.example.fitlifeapplication.ui.meal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifeapplication.R
import com.example.fitlifeapplication.data.Meal
import com.example.fitlifeapplication.databinding.ItemMealSuggestionBinding

class MealSuggestionAdapter(private val meals: List<Meal>) : RecyclerView.Adapter<MealSuggestionAdapter.MealViewHolder>() {

    // Palet warna untuk latar belakang kartu
    private val cardColors = listOf(
        R.color.card_bg_1,
        R.color.card_bg_2,
        R.color.card_bg_3,
        R.color.card_bg_4
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)

        // Atur warna latar belakang kartu secara dinamis
        val colorRes = cardColors[position % cardColors.size]
        val color = ContextCompat.getColor(holder.itemView.context, colorRes)
        holder.binding.mealCardView.setCardBackgroundColor(color)
    }

    override fun getItemCount() = meals.size

    inner class MealViewHolder(val binding: ItemMealSuggestionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            binding.tvMealName.text = meal.name
            binding.tvCalories.text = "${meal.calories} kcal"

            // Set placeholder image (user will add actual images later)
            val context = binding.root.context
            val placeholderId = context.resources.getIdentifier(meal.imagePlaceholder, "drawable", context.packageName)
            if (placeholderId != 0) {
                binding.ivMealImage.setImageResource(placeholderId)
            } else {
                binding.ivMealImage.setImageResource(R.drawable.ic_onboard_food)
            }
        }
    }
}
