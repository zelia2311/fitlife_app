package com.example.fitlifeapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fitlifeapplication.databinding.FragmentMealCaloriesBinding

class MealCaloriesFragment : Fragment() {

    private var _binding: FragmentMealCaloriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealCaloriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Setup progress rings with calories data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
