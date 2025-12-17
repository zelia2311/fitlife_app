package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitlifeapplication.databinding.FragmentHomeBinding
import com.example.fitlifeapplication.databinding.ItemQuickActionBinding
import com.google.android.material.card.MaterialCardView

data class QuickAction(val name: String, val iconRes: Int, val colorRes: Int, val onClick: () -> Unit)

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

        setupQuickActions()

        binding.tvSeeAll.setOnClickListener {
            Toast.makeText(context, "Planner Page Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        binding.workoutCard.setOnClickListener { 
            findNavController().navigate(R.id.nav_workout)
        }
    }

    private fun setupQuickActions() {
        val quickActions = listOf(
            QuickAction("BMI", R.drawable.ic_bmi, R.color.blue) {
                startActivity(Intent(requireContext(), BmiActivity::class.java))
            },
            QuickAction("Workout", R.drawable.ic_play_arrow, R.color.orange) {
                findNavController().navigate(R.id.nav_workout)
            },
            QuickAction("Water", R.drawable.ic_water, R.color.modern_purple) {
                Toast.makeText(context, "Track Water Coming Soon!", Toast.LENGTH_SHORT).show()
            },
            QuickAction("Plan", R.drawable.ic_plan, R.color.green_primary) {
                Toast.makeText(context, "Meal Plan Coming Soon!", Toast.LENGTH_SHORT).show()
            }
        )

        binding.quickActionsContainer.removeAllViews()
        val inflater = LayoutInflater.from(context)

        for (action in quickActions) {
            val actionBinding = ItemQuickActionBinding.inflate(inflater, binding.quickActionsContainer, false)
            
            actionBinding.tvActionName.text = action.name
            actionBinding.ivActionIcon.setImageResource(action.iconRes)
            (actionBinding.actionCard as MaterialCardView).setCardBackgroundColor(ContextCompat.getColor(requireContext(), action.colorRes))
            actionBinding.root.setOnClickListener { action.onClick() }
            
            binding.quickActionsContainer.addView(actionBinding.root)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
