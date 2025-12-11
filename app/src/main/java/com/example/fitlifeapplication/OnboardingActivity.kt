package com.example.fitlifeapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var layoutDots: LinearLayout
    private lateinit var btnNext: Button

    private val layouts = intArrayOf(
        R.layout.slide_one,
        R.layout.slide_two,
        R.layout.slide_three
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        // Inisialisasi
        viewPager = findViewById(R.id.viewPager)
        layoutDots = findViewById(R.id.layoutDots)
        btnNext = findViewById(R.id.btnNext)

        // Adapter
        viewPager.adapter = OnboardingAdapter(layouts)

        // Dots awal
        addDots(0)

        // Animasi saat slide berubah
        viewPager.setPageTransformer { page, position ->
            page.alpha = 0f
            page.translationX = page.width * -position

            when {
                position <= -1 || position >= 1 -> page.alpha = 0f
                position == 0f -> page.alpha = 1f
                else -> page.alpha = 1 - kotlin.math.abs(position)
            }
        }

        // Update dots + text button
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                addDots(position)
                btnNext.text = if (position == layouts.size - 1)
                    "Mulai Sekarang"
                else
                    "Next"
            }
        })

        // Tombol Next/Mulai
        btnNext.setOnClickListener {
            val current = viewPager.currentItem
            if (current < layouts.size - 1) {
                viewPager.currentItem = current + 1
            } else {
                finishOnboarding()
            }
        }
    }

    // Membuat dot indicator
    private fun addDots(position: Int) {
        layoutDots.removeAllViews()

        for (i in layouts.indices) {
            val dot = View(this).apply {
                val size = if (i == position) 26 else 12
                layoutParams = LinearLayout.LayoutParams(size, size).apply {
                    marginEnd = 10
                }
                background = ContextCompat.getDrawable(
                    this@OnboardingActivity,
                    if (i == position) R.drawable.dot_active else R.drawable.dot_inactive
                )
            }
            layoutDots.addView(dot)
        }
    }

    // Setelah onboarding selesai → simpan status & buka Login
    private fun finishOnboarding() {
        val prefs: SharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        prefs.edit().putBoolean("onboard_done", true).apply()

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
