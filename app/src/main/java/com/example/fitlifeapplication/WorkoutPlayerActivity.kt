package com.example.fitlifeapplication

import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class WorkoutPlayerActivity : AppCompatActivity() {

    private val workoutViewModel: WorkoutViewModel by viewModels()
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_player)

        val exercise = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("exercise", Exercise::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("exercise")
        }

        setupRecyclerView()
        setupObservers()
        setupClickListeners()

        if (exercise != null) {
            // Mengirim daftar yang hanya berisi satu latihan ke ViewModel
            workoutViewModel.setExerciseList(listOf(exercise))
        }
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.rvExercises)
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = ExerciseAdapter(emptyList()) { selectedExercise ->
            workoutViewModel.onExerciseClicked(selectedExercise)
        }
        recyclerView.adapter = exerciseAdapter
    }

    private fun setupObservers() {
        val userPlanButton: MaterialButton = findViewById(R.id.btnUserPlan)
        workoutViewModel.userName.observe(this, Observer { name ->
            userPlanButton.text = "$name'S PLAN >"
        })

        // Observer ini sekarang akan menerima daftar dari ViewModel dan memperbarui UI
        workoutViewModel.exerciseList.observe(this, Observer { exercises ->
            exerciseAdapter.updateData(exercises)
        })

        workoutViewModel.completedWorkoutData.observe(this, Observer { completedExercises ->
            println("Workout finished! Navigating to recovery with ${completedExercises.size} exercises.")
            // Implementasi navigasi ke RecoveryFragment
        })
    }

    private fun setupClickListeners() {
        val startWorkoutButton: Button = findViewById(R.id.btnStartWorkout)
        startWorkoutButton.setOnClickListener {
            workoutViewModel.finishWorkout()
        }
    }
}
