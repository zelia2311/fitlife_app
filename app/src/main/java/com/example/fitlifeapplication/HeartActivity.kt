package com.example.fitlifeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivityHeartBinding

class HeartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
