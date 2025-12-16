package com.example.fitlifeapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val exerciseName: String,
    val duration: Int,
    val calories: Int,
    val timestamp: Long,
    val muscleGroup: String // Added this field
)
