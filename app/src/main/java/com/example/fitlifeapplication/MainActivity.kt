package com.example.fitlifeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nav = findViewById<BottomNavigationView>(R.id.bottomNav)

        replaceFragment(HomeFragment())

        nav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_workout -> WorkoutFragment()   // FIXED
                R.id.nav_tracker -> HabitTrackerFragment()
                R.id.nav_profile -> ProfileFragment()   // FIXED
                else -> null
            }

            fragment?.let { replaceFragment(it) }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container, fragment)
            .commit()
    }

    fun openTracker() {
        replaceFragment(HabitTrackerFragment())
    }
}
