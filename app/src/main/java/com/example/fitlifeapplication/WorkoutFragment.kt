package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button

class WorkoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_workout, container, false)

        val btnStart = view.findViewById<Button>(R.id.btnStartWorkout)
        btnStart.setOnClickListener {
            startActivity(Intent(requireContext(), WorkoutPlanActivity::class.java))
        }

        return view
    }
}
