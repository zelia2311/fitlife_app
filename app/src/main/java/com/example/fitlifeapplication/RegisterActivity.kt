package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        binding.btnRegister.setOnClickListener {
            doRegister()
        }

        binding.tvGoLogin.setOnClickListener {
            finish()
        }
    }

    private fun doRegister() {
        val name = binding.etName.text?.toString()?.trim() ?: ""
        val email = binding.etEmailReg.text?.toString()?.trim() ?: ""
        val password = binding.etPasswordReg.text?.toString()?.trim() ?: ""

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Simulate user registration and generate a dummy token
        val dummyToken = "token_for_${email}"
        sessionManager.createLoginSession(dummyToken)

        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()

        // Go to MainActivity
        goToMainActivity()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
