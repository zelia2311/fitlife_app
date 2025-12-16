package com.example.fitlifeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        replaceFragment(HomeFragment())

        nav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_workout -> HomeFragment()   // Placeholder, ganti dengan WorkoutFragment() nanti
                R.id.nav_tracker -> HabitTrackerFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> null
            }

            fragment?.let { replaceFragment(it) }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    fun openTracker() {
        replaceFragment(HabitTrackerFragment())
    }
}
