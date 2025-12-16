package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifeapplication.databinding.ActivityNutritionPlanBinding
import com.example.fitlifeapplication.databinding.ItemMealCardBinding
import com.google.android.material.tabs.TabLayout

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

        // Handle tab selection
        binding.mealTabs.getTabAt(1)?.select() // Select Lunch by default
    }

    private fun setupCalendar() {
        // TODO: Populate the calendar view with days of the week
    }

    private fun setupMealCards() {
        binding.mealsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.mealsRecyclerView.adapter = DummyMealAdapter { meal ->
            val intent = Intent(this, MealDetailActivity::class.java)
            // TODO: Pass meal data to the detail activity
            startActivity(intent)
        }
    }

    private fun setupNutritionists() {
        // TODO: Populate the nutritionists RecyclerView
    }
}

// Dummy Adapter for Meal Cards
class DummyMealAdapter(private val onClick: (Any) -> Unit) : RecyclerView.Adapter<DummyMealAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMealCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onClick(Any()) }
    }

    override fun getItemCount(): Int = 3 // Dummy count

    class ViewHolder(binding: ItemMealCardBinding) : RecyclerView.ViewHolder(binding.root)
}
