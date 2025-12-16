package com.example.fitlifeapplication.ui.meal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fitlifeapplication.databinding.ActivityMealSuggestionBinding

class MealSuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealSuggestionBinding
    private val viewModel: MealSuggestionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealSuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Default category, should be passed from another activity
        val bmiCategory = intent.getStringExtra("BMI_CATEGORY") ?: "Ideal"
        binding.tvBmiCategoryLabel.text = "Based on: $bmiCategory"

        setupRecyclerView()
        observeViewModel()

        viewModel.fetchSuggestions(bmiCategory)
    }

    private fun setupRecyclerView() {
        // Menggunakan GridLayoutManager dengan 2 kolom
        binding.rvMealSuggestions.layoutManager = GridLayoutManager(this, 2)
    }

    private fun observeViewModel() {
        viewModel.suggestions.observe(this) { meals ->
            binding.rvMealSuggestions.adapter = MealSuggestionAdapter(meals)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}
