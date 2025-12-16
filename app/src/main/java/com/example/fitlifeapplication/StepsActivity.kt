package com.example.fitlifeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivityStepsBinding

class StepsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStepsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
