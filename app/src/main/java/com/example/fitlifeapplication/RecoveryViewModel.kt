package com.example.fitlifeapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitlifeapplication.data.AppDatabase
import com.example.fitlifeapplication.data.HistoryEntry
import com.example.fitlifeapplication.data.MuscleGroup
import com.example.fitlifeapplication.data.RecoveryState
import com.example.fitlifeapplication.data.getRecoveryState
import kotlinx.coroutines.launch

class RecoveryViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val _recoveryStates = MutableLiveData<Map<MuscleGroup, RecoveryState>>()
    val recoveryStates: LiveData<Map<MuscleGroup, RecoveryState>> = _recoveryStates

    fun loadRecoveryStates() {
        viewModelScope.launch {
            val states = mutableMapOf<MuscleGroup, RecoveryState>()
            // Menggunakan .entries yang lebih modern dan efisien
            for (muscleGroup in MuscleGroup.values()) {
                val lastWorkout = db.historyDao().getLastWorkoutForMuscleGroup(muscleGroup.name)
                states[muscleGroup] = getRecoveryState(lastWorkout?.timestamp)
            }
            _recoveryStates.postValue(states)
        }
    }

    /**
     * Menganalisis daftar latihan yang telah selesai, menyimpannya ke database,
     * dan memuat ulang status pemulihan.
     */
    fun processCompletedWorkout(completedExercises: List<Exercise>) {
        viewModelScope.launch {
            val timestamp = System.currentTimeMillis()
            val historyEntries = mutableListOf<HistoryEntry>()

            // 1. Buat HistoryEntry untuk setiap latihan yang selesai
            completedExercises.forEach { exercise ->
                // Petakan nama latihan ke grup otot yang sesuai
                // PASTIKAN SEMUA VALUE INI (BICEPS, BACK, etc.) ADA DI FILE ENUM MuscleGroup.kt ANDA
                val muscleGroup = when {
                    exercise.name.contains("Bicep", ignoreCase = true) || 
                    exercise.name.contains("Curls", ignoreCase = true) -> MuscleGroup.BICEPS
                    
                    exercise.name.contains("Row", ignoreCase = true) || 
                    exercise.name.contains("Pull", ignoreCase = true) -> MuscleGroup.BACK
                    
                    exercise.name.contains("Thrust", ignoreCase = true) || 
                    exercise.name.contains("Squat", ignoreCase = true) -> MuscleGroup.GLUTES
                    
                    exercise.name.contains("Abs", ignoreCase = true) || 
                    exercise.name.contains("Crunch", ignoreCase = true) -> MuscleGroup.ABS
                    
                    exercise.name.contains("Shoulder", ignoreCase = true) || 
                    exercise.name.contains("Press", ignoreCase = true) -> MuscleGroup.SHOULDERS
                    
                    exercise.name.contains("Chest", ignoreCase = true) || 
                    exercise.name.contains("Push Up", ignoreCase = true) -> MuscleGroup.CHEST
                    
                    else -> null // Tidak ada grup otot yang cocok
                }

                if (muscleGroup != null) {
                    historyEntries.add(
                        HistoryEntry(
                            exerciseName = exercise.name,
                            muscleGroup = muscleGroup.name,
                            timestamp = timestamp,
                            duration = 300, // Placeholder: durasi dalam detik (misal 5 menit)
                            calories = 50 // Placeholder: kalori yang terbakar
                        )
                    )
                }
            }

            // 2. Simpan entri baru ke database satu per satu
            if (historyEntries.isNotEmpty()) {
                historyEntries.forEach { entry ->
                    db.historyDao().insert(entry) // Menggunakan insert untuk satu entri
                }
            }

            // 3. Muat ulang status dari database untuk memperbarui UI
            loadRecoveryStates()
        }
    }
}
