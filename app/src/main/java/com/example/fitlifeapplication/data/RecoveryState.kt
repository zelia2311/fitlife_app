package com.example.fitlifeapplication.data

import androidx.annotation.ColorRes
import com.example.fitlifeapplication.R

enum class MuscleGroup {
    CHEST,
    SHOULDERS,
    // Add other muscle groups here
}

data class RecoveryState(
    val muscleGroup: MuscleGroup,
    val percentage: Int,
    @ColorRes val color: Int
)

fun getRecoveryState(lastWorkoutTime: Long?): RecoveryState {
    if (lastWorkoutTime == null) {
        return RecoveryState(MuscleGroup.CHEST, 100, R.color.green) // Fresh
    }

    val hoursSinceLastWorkout = (System.currentTimeMillis() - lastWorkoutTime) / (1000 * 60 * 60)

    return when {
        hoursSinceLastWorkout < 24 -> RecoveryState(MuscleGroup.CHEST, 25, R.color.red) // Fatigued
        hoursSinceLastWorkout < 48 -> RecoveryState(MuscleGroup.CHEST, 75, R.color.yellow) // Recovering
        else -> RecoveryState(MuscleGroup.CHEST, 100, R.color.green) // Fresh
    }
}