package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitlifeapplication.ui.bmi.BmiActivity
import com.example.fitlifeapplication.ui.workout.WorkoutPlanActivity
import com.example.fitlifeapplication.ui.tracker.HabitTrackerFragment

class HomeFragment : Fragment() {

    private lateinit var tvGreeting: TextView
    private lateinit var rvPlanner: RecyclerView
    private lateinit var todayWorkout: TextView
    private lateinit var todayCalories: TextView
    private lateinit var todayWater: TextView
    private lateinit var tvPlannerMore: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil ID dari layout baru
        tvGreeting = view.findViewById(R.id.tvGreeting)
        rvPlanner = view.findViewById(R.id.rvPlanner)
        tvPlannerMore = view.findViewById(R.id.tvPlannerMore)

        todayWorkout = view.findViewById(R.id.workout_value)
        todayCalories = view.findViewById(R.id.calories_value)
        todayWater = view.findViewById(R.id.water_value)

        // Personalized greeting
        tvGreeting.text = getGreetingMessage()

        // Tombol Planner → pindah ke Planner Page (belum dibuat)
        tvPlannerMore.setOnClickListener {
            // nanti diarahkan ke halaman planner
        }

        // Quick navigation examples:
        todayWorkout.setOnClickListener {
            startActivity(Intent(requireContext(), WorkoutPlanActivity::class.java))
        }

        todayCalories.setOnClickListener {
            startActivity(Intent(requireContext(), BmiActivity::class.java))
        }

        todayWater.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frag_container, HabitTrackerFragment())
                .addToBackStack(null)
                .commit()
        }

        // Planner list (dummy dulu)
        setupDummyPlanner()
    }

    private fun getGreetingMessage(): String {
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        return when {
            hour < 12 -> "Good Morning!"
            hour < 18 -> "Good Afternoon!"
            else -> "Good Evening!"
        }
    }

    private fun setupDummyPlanner() {
        // Nanti diisi adapter RecyclerView untuk daftar planner
        // rvPlanner.adapter = PlannerAdapter(...)
    }
}
