package com.example.fitlifeapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitlifeapplication.databinding.FragmentWorkoutBinding

class WorkoutFragment : Fragment() {

    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayWorkoutPlan()

        binding.seeAllExercisesButton.setOnClickListener { 
            findNavController().navigate(R.id.action_workoutFragment_to_exerciseListFragment)
        }
    }

    private fun displayWorkoutPlan() {
        val sharedPref = requireActivity().getSharedPreferences("fitlife_prefs", Context.MODE_PRIVATE)
        val bmi = sharedPref.getFloat("latest_bmi", 0.0f).toDouble()

        if (bmi > 0) {
            val category = BmiManager.getCategory(bmi)
            val plan = BmiManager.getPlanFor(category)

            binding.planContainer.removeAllViews()

            plan.forEach { exercise ->
                val exerciseView = TextView(requireContext()).apply {
                    text = "${exercise[0]}: ${exercise[1]}"
                    textSize = 16f
                    setPadding(0, 8, 0, 8)
                }
                binding.planContainer.addView(exerciseView)
            }
        } else {
            // Handle case where no BMI is stored
            binding.planContainer.removeAllViews()
            val noBmiView = TextView(requireContext()).apply {
                text = "Calculate your BMI first to get a workout plan."
                textSize = 16f
            }
            binding.planContainer.addView(noBmiView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}