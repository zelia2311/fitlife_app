package com.example.fitlifeapplication.ui.fitness

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivityFitnessOnboardingBinding

class FitnessOnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFitnessOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitnessOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, FitnessDashboardActivity::class.java)
            startActivity(intent)
            finish() // Optional: finish this activity so user can't go back to it
        }
    }
}
