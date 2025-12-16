package com.example.fitlifeapplication.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.MainActivity
import com.example.fitlifeapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            // Handle login with email and password
        }

        binding.tvRegister.setOnClickListener {
            // Navigate to Register screen
        }

        binding.ibGoogle.setOnClickListener {
            // Handle Google Sign-in
        }

        binding.ibFacebook.setOnClickListener {
            // Handle Facebook Sign-in
        }
    }
}