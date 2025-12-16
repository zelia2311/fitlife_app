package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInConfirmButton.setOnClickListener {
            // Handle sign in logic here
        }

        binding.dontHaveAccountText.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}