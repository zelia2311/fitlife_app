package com.example.fitlifeapplication.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.fitlifeapplication.MainActivity
import com.example.fitlifeapplication.R
import com.example.fitlifeapplication.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make the activity edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // No-op
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == adapter.itemCount - 1) {
                    // Change to check icon on the last slide
                    binding.btnNext.setImageResource(R.drawable.ic_check)
                } else {
                    // Change back to arrow icon
                    binding.btnNext.setImageResource(R.drawable.ic_arrow_forward)
                }
            }
        })

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem == adapter.itemCount - 1) {
                // Save onboarding flag and go to main activity
                val sharedPref = getSharedPreferences("onboarding", MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putBoolean("is_finished", true)
                    apply()
                }
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                // Go to the next slide
                binding.viewPager.currentItem++
            }
        }
    }
}