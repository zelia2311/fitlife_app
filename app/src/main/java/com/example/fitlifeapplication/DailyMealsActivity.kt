package com.example.fitlifeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitlifeapplication.databinding.ActivityDailyMealsBinding

class DailyMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDailyMealsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
