package com.example.fitlifeapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val calories: Int,
    val portion: String,
    val category: String, // "Kurus", "Ideal", "Gemuk", "Obesitas"
    val imagePlaceholder: String // Placeholder for image resource name
)
