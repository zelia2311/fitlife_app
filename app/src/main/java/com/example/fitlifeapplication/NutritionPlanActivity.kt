package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifeapplication.databinding.ActivityNutritionPlanBinding
import com.example.fitlifeapplication.databinding.ItemMealCardBinding
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    val name: String,
    val calories: Int,
    val description: String
) : Parcelable

class NutritionPlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNutritionPlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutritionPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabs()
        setupCalendar()
        setupMealCards()
        setupNutritionists()
    }

    private fun setupTabs() {
        binding.mealTabs.addTab(binding.mealTabs.newTab().setText("Breakfast"))
        binding.mealTabs.addTab(binding.mealTabs.newTab().setText("Lunch"))
        binding.mealTabs.addTab(binding.mealTabs.newTab().setText("Dinner"))

        binding.mealTabs.getTabAt(1)?.select() // Select Lunch by default
    }

    private fun setupCalendar() {
        // TODO: Populate the calendar view with days of the week
    }

    private fun setupMealCards() {
        val dummyMeals = listOf(
            Meal("Oatmeal", 350, "Healthy oatmeal with fruits."),
            Meal("Chicken Salad", 450, "Grilled chicken salad with fresh vegetables."),
            Meal("Salmon and Veggies", 550, "Baked salmon with roasted vegetables.")
        )

        binding.mealsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.mealsRecyclerView.adapter = MealAdapter(dummyMeals) { meal ->
            val intent = Intent(this, MealDetailActivity::class.java)
            intent.putExtra("MEAL_DATA", meal)
            startActivity(intent)
        }
    }

    private fun setupNutritionists() {
        // TODO: Populate the nutritionists RecyclerView
    }
}

class MealAdapter(
    private val meals: List<Meal>,
    private val onClick: (Meal) -> Unit
) : RecyclerView.Adapter<MealAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMealCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
        holder.itemView.setOnClickListener { onClick(meal) }
    }

    override fun getItemCount(): Int = meals.size

    class ViewHolder(private val binding: ItemMealCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            // TODO: Bind meal data to the views in item_meal_card.xml
            // e.g., binding.mealName.text = meal.name
        }
    }
}
