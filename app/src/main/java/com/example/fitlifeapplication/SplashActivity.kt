package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.imgLogo)

        // ANIMASI PREMIUM → Fade-in + Scale
        logo.scaleX = 0.6f
        logo.scaleY = 0.6f
        logo.alpha = 0f

        logo.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(900)
            .setStartDelay(150)
            .start()

        // Delay 2.5 detik sebelum pindah
        Handler(Looper.getMainLooper()).postDelayed({

            val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
            val isDone = prefs.getBoolean("onboard_done", false)

            val next = if (isDone) LoginActivity::class.java else OnboardingActivity::class.java
            startActivity(Intent(this, next))

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()

        }, 2500)
    }
}
