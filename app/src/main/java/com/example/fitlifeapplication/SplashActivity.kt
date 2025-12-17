package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivitySplashBinding
import com.example.fitlifeapplication.ui.login.LoginActivity
import com.example.fitlifeapplication.ui.onboarding.OnboardingActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        Handler(Looper.getMainLooper()).postDelayed({
            // Check if user is signed in (non-null) and update UI accordingly.
            val currentUser = auth.currentUser
            if (currentUser != null) {
                goToMainActivity()
            } else {
                // If not logged in, check if onboarding is completed.
                val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
                val isDone = prefs.getBoolean("onboard_done", false)
                val nextActivity = if (isDone) LoginActivity::class.java else OnboardingActivity::class.java
                startActivity(Intent(this, nextActivity))
                finish()
            }
        }, 2500)
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
