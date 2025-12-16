package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitlifeapplication.databinding.FragmentHomeBinding
import com.example.fitlifeapplication.BmiActivity
import com.example.fitlifeapplication.WorkoutPlanActivity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Personalized greeting
        binding.tvWelcome.text = getGreetingMessage()

        // Tombol Planner → pindah ke Planner Page (belum dibuat)
        binding.tvSeeMore.setOnClickListener {
            Toast.makeText(context, "Planner Page Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        // Quick navigation examples:
        // Note: You should ideally navigate to other fragments, not start new activities if they are part of the same flow.
        binding.mainCard.setOnClickListener {
            startActivity(Intent(requireContext(), BmiActivity::class.java))
        }

        binding.rvNutritionists.setOnClickListener { // Assuming you want to navigate from here
            startActivity(Intent(requireContext(), WorkoutPlanActivity::class.java))
        }

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
        // binding.rvNutritionists.adapter = ...
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
