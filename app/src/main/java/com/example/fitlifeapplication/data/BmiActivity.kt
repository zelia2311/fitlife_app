package com.example.fitlifeapplication

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifeapplication.logic.BmiCalculator
import com.example.fitlifeapplication.meal.MealAdapter
import com.example.fitlifeapplication.meal.MealData

class BmiActivity : AppCompatActivity() {

    private lateinit var etHeight: EditText
    private lateinit var etWeight: EditText
    private lateinit var tvBmiValue: TextView
    private lateinit var tvCategory: TextView
    private lateinit var tvRecommendation: TextView
    private lateinit var btnCalc: Button

    // Gauge Views
    private lateinit var imgNeedle: ImageView

    // Meal Suggestion Recycler
    private lateinit var rvMeals: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        etHeight = findViewById(R.id.etHeight)
        etWeight = findViewById(R.id.etWeight)
        tvBmiValue = findViewById(R.id.tvBmiValue)
        tvCategory = findViewById(R.id.tvCategory)
        tvRecommendation = findViewById(R.id.tvRecommendation)
        btnCalc = findViewById(R.id.btnCalc)

        imgNeedle = findViewById(R.id.imgNeedle)
        rvMeals = findViewById(R.id.rvMeals)

        btnCalc.setOnClickListener { calculate() }
    }

    private fun calculate() {
        val height = etHeight.text.toString().toFloatOrNull()
        val weight = etWeight.text.toString().toFloatOrNull()

        if (height == null || weight == null) {
            tvCategory.text = getString(R.string.invalid_input)
            return
        }

        val bmi = BmiCalculator.calculateBmi(weight, height)
        val category = BmiCalculator.getCategory(bmi)
        val rec = BmiCalculator.getRecommendations(category)

        // =============================
        // 1. TAMPILKAN NILAI BMI
        // =============================
        tvBmiValue.text = String.format("%.1f", bmi)
        tvCategory.text = category.name

        tvRecommendation.text =
            "Workout: ${rec.workoutType}\nDiet: ${rec.diet}"

        // =============================
        // 2. ANIMASI JARUM GAUGE
        // =============================
        animateGauge(bmi)

        // =============================
        // 3. LOAD MEAL SUGGESTION SESUAI BMI
        // =============================
        val meals = MealData.getSuggestion(category)
        rvMeals.adapter = MealAdapter(meals)
    }

    /** Animasi jarum gauge BMI */
    private fun animateGauge(bmi: Float) {
        val angle = when {
            bmi < 18.5f -> -60f
            bmi < 25f -> 0f
            bmi < 30f -> 60f
            else -> 120f
        }

        ObjectAnimator.ofFloat(imgNeedle, "rotation", angle).apply {
            duration = 1200
            interpolator = DecelerateInterpolator()
            start()
        }
    }
}
