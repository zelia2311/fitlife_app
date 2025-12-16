package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
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
        val plan = BmiManager.getPlanFor(category)

        binding.rvPlan.layoutManager = LinearLayoutManager(this)
        binding.rvPlan.adapter = PlanAdapter(plan, object : PlanAdapter.OnStartClick {
            override fun onStart(v: View) {
                val exercise = v.tag as Array<String>
                val intent = Intent(this@WorkoutPlanActivity, WorkoutPlayerActivity::class.java)
                intent.putExtra("exercise_name", exercise[0])
                intent.putExtra("exercise_info", exercise[1])
                startActivity(intent)
            }
        })
    }
}
