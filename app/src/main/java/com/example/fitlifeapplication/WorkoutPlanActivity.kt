package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitlifeapplication.databinding.ActivityWorkoutPlanBinding

class WorkoutPlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutPlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bmi = intent.getDoubleExtra("bmi_value", 22.0)
        val category = BmiManager.getCategory(bmi)

        // Membuat daftar Exercise lengkap dengan data dari BmiManager dan beberapa placeholder
        // TODO: Ganti placeholder (sets, reps, imageUrl, dll.) dengan data yang sebenarnya jika tersedia
        val plan = BmiManager.getPlanFor(category).map {
            Exercise(
                name = it[0],
                // Info dari BmiManager (it[1]) bisa dimasukkan ke deskripsi atau di-parse
                // Untuk saat ini, kita gunakan nilai default untuk set dan repetisi
                sets = 3,
                reps = 12,
                imageUrl = "img_workout_placeholder", // Placeholder drawable
                bodyPartImageUrl = "ic_workout" // Placeholder drawable
            )
        }

        binding.rvPlan.layoutManager = LinearLayoutManager(this)
        binding.rvPlan.adapter = PlanAdapter(plan, object : PlanAdapter.OnStartClick {
            override fun onStart(exercise: Exercise) {
                val intent = Intent(this@WorkoutPlanActivity, WorkoutPlayerActivity::class.java)
                // Mengirim seluruh objek Exercise ke aktivitas berikutnya
                intent.putExtra("exercise", exercise)
                startActivity(intent)
            }
        })
    }
}
