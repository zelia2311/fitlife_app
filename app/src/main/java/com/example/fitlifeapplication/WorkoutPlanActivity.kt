package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutPlanActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_plan)

        rv = findViewById(R.id.rvPlan)

        val bmi = intent.getDoubleExtra("bmi_value", 22.0)
        val category = BmiManager.getCategory(bmi)
        val plan = BmiManager.getPlanFor(category)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = PlanAdapter(plan, object : PlanAdapter.OnStartClick {
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
