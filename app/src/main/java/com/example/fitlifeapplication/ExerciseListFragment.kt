package com.example.fitlifeapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitlifeapplication.data.Exercise
import com.example.fitlifeapplication.data.MuscleGroup
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
            Exercise("Bench Press", MuscleGroup.CHEST),
            Exercise("Overhead Press", MuscleGroup.SHOULDERS),
            Exercise("Bicep Curls", MuscleGroup.SHOULDERS), // Biceps are often worked with shoulders/back
            Exercise("Pull-ups", MuscleGroup.SHOULDERS),    // Back and biceps
            Exercise("Squats", MuscleGroup.CHEST),         // Legs, but using CHEST for demo
            Exercise("Deadlifts", MuscleGroup.SHOULDERS)  // Full body, using SHOULDERS for demo
        )

        // 2. Create and set up the adapter
        val exerciseAdapter = ExerciseAdapter(exercises)
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
