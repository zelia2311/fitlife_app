package com.example.fitlifeapplication.ui.fitness

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivityFitnessAnalysisBinding

class FitnessAnalysisActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFitnessAnalysisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitnessAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Logic for the analysis screen will be added here in the future.
    }
}
