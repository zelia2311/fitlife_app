package com.example.fitlifeapplication.ui.bmi

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.R
import com.example.fitlifeapplication.logic.BmiCalculator
import com.google.android.material.card.MaterialCardView

class BmiActivity : AppCompatActivity() {

    private lateinit var etHeight: TextView
    private lateinit var etWeight: TextView
    private lateinit var tvBmiValue: TextView
    private lateinit var tvCategory: TextView
    private lateinit var tvRecommendation: TextView
    private lateinit var btnCalc: Button
    private lateinit var cardResult: MaterialCardView
    private lateinit var imgNeedle: ImageView

    private var resultShownOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        etHeight = findViewById(R.id.etHeight)
        etWeight = findViewById(R.id.etWeight)
        tvBmiValue = findViewById(R.id.tvBmiValue)
        tvCategory = findViewById(R.id.tvCategory)
        tvRecommendation = findViewById(R.id.tvRecommendation)
        btnCalc = findViewById(R.id.btnCalc)
        cardResult = findViewById(R.id.cardResult)
        imgNeedle = findViewById(R.id.imgNeedle)

        // hide result card before animation
        cardResult.alpha = 0f
        cardResult.translationY = 50f

        // initial needle rotation (far left)
        imgNeedle.rotation = -90f

        btnCalc.setOnClickListener { calculateAndAnimate() }
    }

    private fun calculateAndAnimate() {
        val height = etHeight.text.toString().toFloatOrNull()
        val weight = etWeight.text.toString().toFloatOrNull()

        if (height == null || height <= 0f || weight == null || weight <= 0f) {
            tvCategory.text = getString(R.string.invalid_input)
            return
        }

        val bmi = BmiCalculator.calculateBmi(weight, height)
        val category = BmiCalculator.getCategory(bmi)
        val rec = BmiCalculator.getRecommendations(category)

        tvBmiValue.text = String.format("%.1f", bmi)

        val categoryText = when (category) {
            BmiCalculator.Category.UNDERWEIGHT -> "Underweight"
            BmiCalculator.Category.NORMAL -> "Normal"
            BmiCalculator.Category.OVERWEIGHT -> "Overweight"
            BmiCalculator.Category.OBESE -> "Obese"
        }
        tvCategory.text = categoryText

        tvRecommendation.text =
            "Workout: ${rec.workoutType}\nDiet: ${rec.diet}"

        animateGauge(bmi)
        animateResultCardIfNeeded()
    }

    private fun animateGauge(bmi: Float) {
        val clamped = bmi.coerceIn(10f, 40f)
        val fraction = (clamped - 10f) / 30f
        val targetAngle = -90f + (fraction * 180f)

        val animator = ObjectAnimator.ofFloat(
            imgNeedle,
            "rotation",
            imgNeedle.rotation,
            targetAngle
        )
        animator.duration = 800
        animator.interpolator = DecelerateInterpolator()
        animator.start()
    }

    private fun animateResultCardIfNeeded() {
        if (!resultShownOnce) {
            resultShownOnce = true
            cardResult.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(500)
                .setInterpolator(DecelerateInterpolator())
                .start()
        }
    }
}
