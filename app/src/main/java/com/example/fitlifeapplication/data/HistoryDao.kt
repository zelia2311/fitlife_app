package com.example.fitlifeapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    fun insert(entry: HistoryEntry)

    @Query("SELECT * FROM history ORDER BY timestamp DESC")
    fun getAll(): List<HistoryEntry>

    @Query("SELECT * FROM history WHERE muscleGroup = :muscleGroup ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastWorkoutForMuscleGroup(muscleGroup: String): HistoryEntry?
}
