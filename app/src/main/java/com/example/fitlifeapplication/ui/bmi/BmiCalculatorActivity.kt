package com.example.fitlifeapplication.ui.bmi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitlifeapplication.databinding.ActivityBmiCalculatorBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class BmiCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmiCalculatorBinding
    private val weightHistory = mutableListOf<WeightHistory>()
    private lateinit var historyAdapter: HistoryAdapter

    // Dummy user height, replace with actual user data
    private val userHeightCm = 170f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        populateDummyHistory()

        binding.btnNewWeight.setOnClickListener {
            val intent = Intent(this, NewWeightActivity::class.java)
            newWeightLauncher.launch(intent)
        }

        // Initial BMI Calculation
        if (weightHistory.isNotEmpty()) {
            calculateBmi(weightHistory.first().weight.toFloat())
        }
    }

    private val newWeightLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newWeight = result.data?.getDoubleExtra("new_weight", 0.0) ?: 0.0
            if (newWeight > 0) {
                // Add to top of history
                weightHistory.add(0, WeightHistory("Today", newWeight))
                historyAdapter.notifyItemInserted(0)
                binding.rvHistory.scrollToPosition(0)

                // Recalculate BMI with new weight
                calculateBmi(newWeight.toFloat())
            }
        }
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter(weightHistory)
        binding.rvHistory.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(this@BmiCalculatorActivity)
        }
    }

    private fun populateDummyHistory() {
        weightHistory.add(WeightHistory("Today", 62.5))
        weightHistory.add(WeightHistory("Yesterday", 62.8))
        weightHistory.add(WeightHistory("2 days ago", 63.1))
        historyAdapter.notifyDataSetChanged()
    }

    private fun calculateBmi(weight: Float) {
        if (userHeightCm > 0 && weight > 0) {
            val heightInMeters = userHeightCm / 100
            val bmi = weight / (heightInMeters * heightInMeters)

            // Save BMI to SharedPreferences
            val sharedPref = getSharedPreferences("fitlife_prefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putFloat("latest_bmi", bmi)
                apply()
            }

            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            val formattedBmi = df.format(bmi)

            binding.tvBmiResult.text = formattedBmi
            displayBmiCategory(bmi)
        } else {
            binding.tvBmiResult.text = "N/A"
            binding.tvBmiCategory.text = "Invalid data"
        }
    }

    private fun displayBmiCategory(bmi: Float) {
        val categoryText: String
        val progress: Int

        when {
            bmi < 18.5 -> {
                categoryText = "Kurus"
                progress = 15
            }
            bmi < 25 -> {
                categoryText = "Ideal"
                progress = 45
            }
            bmi < 30 -> {
                categoryText = "Gemuk"
                progress = 75
            }
            else -> {
                categoryText = "Obesitas"
                progress = 95
            }
        }
        binding.tvBmiCategory.text = categoryText
        binding.bmiGauge.progress = progress
    }
}