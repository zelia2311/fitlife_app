package com.example.fitlifeapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.SleepSessionRecord
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.TotalCaloriesBurnedRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import com.example.fitlifeapplication.databinding.ActivityHomeBinding
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var healthConnectClient: HealthConnectClient

    private val PERMISSIONS =
        setOf(
            HealthPermission.getReadPermission(StepsRecord::class),
            HealthPermission.getReadPermission(HeartRateRecord::class),
            HealthPermission.getReadPermission(SleepSessionRecord::class),
            HealthPermission.getReadPermission(TotalCaloriesBurnedRecord::class)
        )

    private val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        lifecycleScope.launch {
            if (permissions.all { it.value }) {
                readData()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (HealthConnectClient.isProviderAvailable(this)) {
            healthConnectClient = HealthConnectClient.getOrCreate(this)
            checkAndRequestPermissions()
        } else {
            // Handle Health Connect not available
        }

        binding.stepsCard.setOnClickListener {
            startActivity(Intent(this, StepsActivity::class.java))
        }

        binding.sleepCard.setOnClickListener {
            startActivity(Intent(this, SleepActivity::class.java))
        }

        binding.heartCard.setOnClickListener {
            startActivity(Intent(this, HeartActivity::class.java))
        }

        binding.kcalCard.setOnClickListener {
            startActivity(Intent(this, KcalActivity::class.java))
        }

        binding.dailyMealsButton1.setOnClickListener {
            startActivity(Intent(this, NutritionPlanActivity::class.java))
        }

        binding.dailyMealsButton2.setOnClickListener {
            startActivity(Intent(this, DailyMealsActivity::class.java))
        }
    }

    private fun checkAndRequestPermissions() {
        lifecycleScope.launch {
            val granted = healthConnectClient.permissionController.getGrantedPermissions()
            if (granted.containsAll(PERMISSIONS)) {
                readData()
            } else {
                requestPermissions.launch(PERMISSIONS.toTypedArray())
            }
        }
    }

    private fun readData() {
        lifecycleScope.launch {
            readStepsData()
            readHeartRateData()
            readSleepData()
            readCaloriesData()
        }
    }

    private suspend fun readStepsData() {
        val today = ZonedDateTime.now()
        val startOfDay = today.toLocalDate().atStartOfDay(today.zone).toInstant()
        val timeRangeFilter = TimeRangeFilter.between(startOfDay, Instant.now())

        try {
            val response = healthConnectClient.readRecords(ReadRecordsRequest(StepsRecord::class, timeRangeFilter))
            val totalSteps = response.records.sumOf { it.count }
            binding.stepsValue.text = totalSteps.toString()
            binding.stepsProgress.progress = totalSteps.toInt()
        } catch (e: Exception) {
            // Handle exception
        }
    }

    private suspend fun readHeartRateData() {
        val today = ZonedDateTime.now()
        val startOfDay = today.toLocalDate().atStartOfDay(today.zone).toInstant()
        val timeRangeFilter = TimeRangeFilter.between(startOfDay, Instant.now())

        try {
            val response = healthConnectClient.readRecords(ReadRecordsRequest(HeartRateRecord::class, timeRangeFilter))
            val averageHeartRate = response.records.flatMap { it.samples }.map { it.beatsPerMinute }.average()
            if (!averageHeartRate.isNaN()) {
                binding.heartRateValue.text = averageHeartRate.toInt().toString()
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }

    private suspend fun readSleepData() {
        val yesterday = ZonedDateTime.now().minus(1, ChronoUnit.DAYS)
        val startOfYesterday = yesterday.toLocalDate().atStartOfDay(yesterday.zone).toInstant()
        val timeRangeFilter = TimeRangeFilter.between(startOfYesterday, Instant.now())

        try {
            val response = healthConnectClient.readRecords(ReadRecordsRequest(SleepSessionRecord::class, timeRangeFilter))
            val totalSleepMinutes = response.records.sumOf {
                ChronoUnit.MINUTES.between(it.startTime, it.endTime)
            }
            val hours = totalSleepMinutes / 60
            val minutes = totalSleepMinutes % 60
            binding.sleepValue.text = String.format("%d:%02dh", hours, minutes)
        } catch (e: Exception) {
            // Handle exception
        }
    }

    private suspend fun readCaloriesData() {
        val today = ZonedDateTime.now()
        val startOfDay = today.toLocalDate().atStartOfDay(today.zone).toInstant()
        val timeRangeFilter = TimeRangeFilter.between(startOfDay, Instant.now())

        try {
            val response = healthConnectClient.readRecords(ReadRecordsRequest(TotalCaloriesBurnedRecord::class, timeRangeFilter))
            val totalCalories = response.records.sumOf { it.energy.inKilocalories }
            binding.kcalValue.text = totalCalories.toInt().toString()
            binding.kcalProgress.progress = totalCalories.toInt()
        } catch (e: Exception) {
            // Handle exception
        }
    }
}

fun HealthConnectClient.Companion.isProviderAvailable(activity: HomeActivity): Boolean {
    return TODO("Provide the return value")
}
