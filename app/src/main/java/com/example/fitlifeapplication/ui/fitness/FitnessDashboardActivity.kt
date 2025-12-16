package com.example.fitlifeapplication.ui.fitness

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitlifeapplication.R
import com.example.fitlifeapplication.databinding.ActivityFitnessDashboardBinding

class FitnessDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFitnessDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitnessDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGoalList()

        binding.ivProfilePic.setOnClickListener {
            val intent = Intent(this, FitnessAnalysisActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupGoalList() {
        val goals = listOf(
            ExerciseGoal("Warm-Up Exercise", "12 items", R.drawable.ic_onboard_yoga_background),
            ExerciseGoal("Stretching Muscles", "15 items", R.drawable.ic_onboard_running_background)
        )

        val adapter = ExerciseGoalAdapter(goals)
        binding.rvGoals.adapter = adapter
        binding.rvGoals.layoutManager = LinearLayoutManager(this)
    }
}
