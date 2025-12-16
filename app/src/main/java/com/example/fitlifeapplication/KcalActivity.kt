package com.example.fitlifeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivityKcalBinding

class KcalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKcalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKcalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
