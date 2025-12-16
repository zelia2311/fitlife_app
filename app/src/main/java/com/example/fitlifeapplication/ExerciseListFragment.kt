package com.example.fitlifeapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitlifeapplication.databinding.FragmentExerciseListBinding

class ExerciseListFragment : Fragment() {

    private var _binding: FragmentExerciseListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Create a sample list of exercises
        val exercises = listOf(
            Exercise("Bench Press", 3, 10, 100, "", ""),
            Exercise("Overhead Press", 3, 10, 70, "", ""),
            Exercise("Bicep Curls", 3, 10, 30, "", ""), // Biceps are often worked with shoulders/back
            Exercise("Pull-ups", 3, 10, null, "", ""),    // Back and biceps
            Exercise("Squats", 3, 10, 150, "", ""),         // Legs, but using CHEST for demo
            Exercise("Deadlifts", 3, 10, 200, "", "")  // Full body, using SHOULDERS for demo
        )

        // 2. Create and set up the adapter
        val exerciseAdapter = ExerciseAdapter(exercises) { exercise ->
            // Handle exercise click
            Toast.makeText(context, "Clicked on ${exercise.name}", Toast.LENGTH_SHORT).show()
        }
        binding.exerciseRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = exerciseAdapter
        }

        // TODO: Implement logic for "Add an exercise"
        // TODO: Implement logic for "Start Workout" button (perhaps to start a multi-exercise session)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
