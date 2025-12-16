package com.example.fitlifeapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.logic.BmiCalculator

class BmiActivity : AppCompatActivity() {

    private var height: Int = 161
    private var weight: Int = 87
    private var age: Int = 29

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showStartScreen()
    }

    private fun showStartScreen() {
        setContentView(R.layout.activity_bmi_start)
        findViewById<Button>(R.id.start_button).setOnClickListener {
            showInputScreen()
        }
    }

    private fun showInputScreen() {
        setContentView(R.layout.activity_bmi_input)

        val heightPickerM = findViewById<NumberPicker>(R.id.height_picker_m)
        val heightPickerCm = findViewById<NumberPicker>(R.id.height_picker_cm)
        val weightValue = findViewById<EditText>(R.id.weight_value)
        val ageValue = findViewById<EditText>(R.id.age_value)

        // Height Picker setup
        heightPickerM.minValue = 1
        heightPickerM.maxValue = 2
        heightPickerM.value = height / 100

        heightPickerCm.minValue = 0
        heightPickerCm.maxValue = 99
        heightPickerCm.value = height % 100

        heightPickerM.setOnValueChangedListener { _, _, newVal ->
            height = newVal * 100 + heightPickerCm.value
        }
        heightPickerCm.setOnValueChangedListener { _, _, newVal ->
            height = heightPickerM.value * 100 + newVal
        }

        // Set initial values for EditText
        weightValue.setText(weight.toString())
        ageValue.setText(age.toString())

        findViewById<Button>(R.id.calculate_button).setOnClickListener {
            weight = weightValue.text.toString().toIntOrNull() ?: weight
            age = ageValue.text.toString().toIntOrNull() ?: age

            val bmi = BmiCalculator.calculateBmi(weight.toFloat(), height.toFloat() / 100)
            val category = BmiCalculator.getCategory(bmi)
            showResultScreen(bmi, category)
        }
    }

    private fun showResultScreen(bmi: Float, category: BmiCalculator.Category) {
        setContentView(R.layout.activity_bmi_result)

        findViewById<TextView>(R.id.bmi_value).text = String.format("%.1f", bmi)
        findViewById<TextView>(R.id.bmi_category).text = when (category) {
            BmiCalculator.Category.UNDERWEIGHT -> "Underweight"
            BmiCalculator.Category.OPTIMAL -> "Optimal"
            BmiCalculator.Category.OVERWEIGHT -> "Overweight"
            BmiCalculator.Category.OBESE -> "Obese"
        }
        findViewById<TextView>(R.id.height_weight_info).text = "Height: $height cm | Weight: $weight kg"

        findViewById<Button>(R.id.retry_button).setOnClickListener {
            showInputScreen()
        }
    }
}
