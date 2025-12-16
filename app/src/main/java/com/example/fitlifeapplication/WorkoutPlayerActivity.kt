package com.example.fitlifeapplication

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

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.rvExercises)
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = ExerciseAdapter(emptyList()) { exercise ->
            workoutViewModel.onExerciseClicked(exercise)
            // Di sini Anda bisa navigasi ke layar detail latihan jika perlu
        }
        recyclerView.adapter = exerciseAdapter
    }

    private fun setupObservers() {
        // Mengamati perubahan nama pengguna
        val userPlanButton: MaterialButton = findViewById(R.id.btnUserPlan)
        workoutViewModel.userName.observe(this, Observer { name ->
            userPlanButton.text = "$name'S PLAN >"
        })

        // Mengamati perubahan daftar latihan
        workoutViewModel.exerciseList.observe(this, Observer { exercises ->
            exerciseAdapter.updateData(exercises)
        })

        // Mengamati data yang selesai untuk navigasi (contoh)
        workoutViewModel.completedWorkoutData.observe(this, Observer { completedExercises ->
            // Data siap dikirim!
            // Di sini Anda akan menavigasi ke RecoveryFragment
            // dan meneruskan data jika diperlukan (meskipun ViewModel sudah cukup)
            println("Workout finished! Navigating to recovery with ${completedExercises.size} exercises.")

            // Contoh navigasi (ganti dengan implementasi navigasi Anda, misal Jetpack Navigation)
            // val recoveryFragment = RecoveryFragment()
            // supportFragmentManager.beginTransaction()
            //     .replace(R.id.fragment_container, recoveryFragment)
            //     .addToBackStack(null)
            //     .commit()
        })
    }

    private fun setupClickListeners() {
        val startWorkoutButton: Button = findViewById(R.id.btnStartWorkout)
        startWorkoutButton.setOnClickListener {
            workoutViewModel.finishWorkout()
        }
    }
}
