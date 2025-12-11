package com.example.fitlifeapplication

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// 🔹 PENTING: import Room classes dari package data
import com.example.fitlifeapplication.data.AppDatabase
import com.example.fitlifeapplication.data.HistoryEntry

class WorkoutPlayerActivity : AppCompatActivity() {

    private lateinit var tvTimer: TextView
    private lateinit var btnPlay: ImageButton
    private lateinit var btnFinish: Button

    private var timeLeft = 30_000L
    private var timer: CountDownTimer? = null
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_player)

        tvTimer = findViewById(R.id.tvTimer)
        btnPlay = findViewById(R.id.btnPlay)
        btnFinish = findViewById(R.id.btnFinish)

        updateTimerText()

        btnPlay.setOnClickListener {
            if (!running) startTimer() else pauseTimer()
        }

        btnFinish.setOnClickListener {
            saveWorkout()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateTimerText()
                running = true
            }

            override fun onFinish() {
                running = false
                showRestDialog()
            }
        }.start()
    }

    private fun pauseTimer() {
        timer?.cancel()
        running = false
    }

    private fun updateTimerText() {
        val sec = (timeLeft / 1000).toInt()
        tvTimer.text = String.format("00:%02d", sec)
    }

    private fun showRestDialog() {
        AlertDialog.Builder(this)
            .setTitle("Rest Time")
            .setMessage("Take 30 seconds rest?")
            .setPositiveButton("Next") { _, _ -> resetTimer() }
            .setNegativeButton("Finish") { _, _ -> saveWorkout() }
            .show()
    }

    private fun resetTimer() {
        timeLeft = 30_000L
        updateTimerText()
    }

    private fun saveWorkout() {
        val exercise = intent.getStringExtra("exercise_name") ?: "Unknown Exercise"

        // kalau di entity kamu duration tipe Long ganti 30 jadi 30L
        val entry = HistoryEntry(
            exerciseName = exercise,
            duration = 30,
            calories = 5,
            timestamp = System.currentTimeMillis()
        )

        // Simpan ke Room di background thread
        Thread {
            val db = AppDatabase.getInstance(this)
            db.historyDao().insert(entry)

            runOnUiThread {
                Toast.makeText(this, "Workout saved", Toast.LENGTH_SHORT).show()
                finish()
            }
        }.start()
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }
}
