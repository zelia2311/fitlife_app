package com.example.fitlifeapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // Untuk saat ini kita cuma simulasi "registrasi berhasil"
        Toast.makeText(this, "Account created, please login", Toast.LENGTH_SHORT).show()

        // Kembali ke Login dengan email terisi otomatis
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("email_prefill", email)
        startActivity(intent)
        finish()
    }
}
