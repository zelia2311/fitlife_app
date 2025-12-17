package com.example.fitlifeapplication.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.fitlifeapplication.R
import com.example.fitlifeapplication.databinding.ActivityOnboardingBinding
import com.example.fitlifeapplication.ui.login.LoginActivity
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == adapter.itemCount - 1) {
                    binding.btnNext.setImageResource(R.drawable.ic_check)
                } else {
                    binding.btnNext.setImageResource(R.drawable.ic_arrow_forward)
                }
            }
        })

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem == adapter.itemCount - 1) {
                // Save onboarding flag to the correct shared preference
                val sharedPref = getSharedPreferences("app_prefs", MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putBoolean("onboard_done", true)
                    apply()
                }
                // After onboarding, navigate to LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                binding.viewPager.currentItem++
            }
        }
    }
}