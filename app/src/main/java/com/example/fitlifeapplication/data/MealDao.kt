package com.example.fitlifeapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(meals: List<Meal>)

    @Query("SELECT * FROM meals WHERE category = :bmiCategory ORDER BY RANDOM() LIMIT 9")
    suspend fun getMealSuggestions(bmiCategory: String): List<Meal>

    @Query("SELECT COUNT(*) FROM meals")
    suspend fun getMealCount(): Int
}
