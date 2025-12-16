package com.example.fitlifeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitlifeapplication.databinding.ActivityMealDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class MealDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this)
        binding.mealDetailViewpager.adapter = adapter

        TabLayoutMediator(binding.mealDetailTabs, binding.mealDetailViewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Ingredients"
                1 -> "Calories"
                else -> "Video"
            }
        }.attach()
    }

    private inner class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MealIngredientsFragment()
                else -> MealCaloriesFragment()
            }
        }
    }
}
