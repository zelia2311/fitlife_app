package com.example.fitlifeapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HabitTrackerFragment : Fragment() {

    private val habits = arrayListOf(
        Habit("Drink 2L Water"),
        Habit("Sleep 8 Hours"),
        Habit("Workout"),
        Habit("Eat Healthy"),
        Habit("10K Steps")
    )

    private lateinit var adapter: HabitAdapter
    private lateinit var progressCircle: ProgressBar
    private lateinit var tvProgress: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_habit_tracker, container, false)

        progressCircle = view.findViewById(R.id.progressCircle)
        tvProgress = view.findViewById(R.id.tvProgressText)

        val rv = view.findViewById<RecyclerView>(R.id.recyclerHabits)
        rv.layoutManager = LinearLayoutManager(requireContext())

        adapter = HabitAdapter(requireContext(), habits) { updateProgress() }
        rv.adapter = adapter

        updateProgress()
        return view
    }

    private fun updateProgress() {
        val done = habits.count { it.completed }
        val progress = (done * 100) / habits.size

        progressCircle.progress = progress
        tvProgress.text = "$progress%"
    }
}
