package com.example.fitlifeapplication.ui.bmi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivityNewWeightBinding
import java.text.DecimalFormat

class NewWeightActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewWeightBinding
    private var selectedWeight: Double = 62.5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWeightPicker()

        binding.btnSave.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("new_weight", selectedWeight)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun setupWeightPicker() {
        binding.sbWeightPicker.max = 1500 // Representing weights from 0.0 to 150.0 kg
        binding.sbWeightPicker.progress = (selectedWeight * 10).toInt()
        updateWeightText(selectedWeight)

        binding.sbWeightPicker.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                selectedWeight = progress / 10.0
                updateWeightText(selectedWeight)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun updateWeightText(weight: Double) {
        val df = DecimalFormat("#.0'kg'")
        binding.tvSelectedWeight.text = df.format(weight)
    }
}
