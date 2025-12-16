package com.example.fitlifeapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.fitlifeapplication.data.MuscleGroup
import com.example.fitlifeapplication.databinding.FragmentRecoveryBinding

class RecoveryFragment : Fragment() {

    private var _binding: FragmentRecoveryBinding? = null
    private val binding get() = _binding!!

    private val recoveryViewModel: RecoveryViewModel by viewModels()
    private val workoutViewModel: WorkoutViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recoveryViewModel.recoveryStates.observe(viewLifecycleOwner) { states ->
            states[MuscleGroup.CHEST]?.let {
                binding.chestMuscleImage.setColorFilter(ContextCompat.getColor(requireContext(), it.color))
                binding.chestPercentageText.text = getString(R.string.percentage_format, it.percentage)
            }
            states[MuscleGroup.SHOULDERS]?.let {
                binding.shouldersMuscleImage.setColorFilter(ContextCompat.getColor(requireContext(), it.color))
                binding.shoulderPercentageText.text = getString(R.string.percentage_format, it.percentage)
            }
        }

        workoutViewModel.completedWorkoutData.observe(viewLifecycleOwner) { completedExercises ->
            if (completedExercises?.isNotEmpty() == true) {
                recoveryViewModel.processCompletedWorkout(completedExercises)
            }
        }

        binding.startWorkoutButton.setOnClickListener {
            val containerId = (view.parent as ViewGroup).id
            parentFragmentManager.beginTransaction()
                .replace(containerId, WorkoutFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        recoveryViewModel.loadRecoveryStates()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
